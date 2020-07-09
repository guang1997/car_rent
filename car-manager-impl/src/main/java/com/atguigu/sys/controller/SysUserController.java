package com.atguigu.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.ResultObj;
import com.atguigu.car.utils.SysConstast;
import com.atguigu.car.utils.WebUtils;
import com.atguigu.sys.bean.SysUser;
import com.atguigu.sys.service.LogInfoService;
import com.atguigu.sys.service.SysUserService;
import com.atguigu.sys.service.UserRoleService;
import com.atguigu.sys.vo.LogInfoVo;
import com.atguigu.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@RequestMapping(value = "/user")
@Controller
public class SysUserController {

    @Autowired
    SysUserService userService;

    @Autowired
    UserRoleService userRoleService;

    /**
     * 用于重置密码
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/changeUserPwd")
    public ResultObj changeUserPwd(Integer userid) {
        try {
            userService.changeUserPwd(userid);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 用于保存用户角色
     *
     * @param userVo
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/saveUserRole")
    public ResultObj saveUserRole(UserVo userVo) {
        try {
            userRoleService.saveUserRole(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 用于初始化角色列表
     *
     * @param userVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/initUserRoleTable")
    public DataGridView initUserRoleTable(UserVo userVo) {
        DataGridView dataGridView = userService.initUserRoleTable(userVo);
        return dataGridView;
    }

    @Autowired
    LogInfoService logInfoService;

    /**
     * 该方法用于验证用户登陆
     *
     * @return
     */
    @PostMapping(value = "/login")
    public String doLogin(UserVo userVo, Model model) {
        String code = WebUtils.getHttpSession().getAttribute("code").toString();
        if (userVo.getCode().equals(code)) {
            SysUser user = userService.doLogin(userVo);
            if (user != null) {
                // 如果登陆成功，将数据添加到session域中，并且跳转到主页面
                WebUtils.getHttpSession().setAttribute("user", user);
                //用户登录成功之后，将日志信息存到数据库中
                LogInfoVo logInfoVo = new LogInfoVo();
                logInfoVo.setLoginname(user.getLoginname() + "-" + user.getRealname());
                logInfoVo.setLogintime(new Date());
                logInfoVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());
                logInfoService.addLogInfo(logInfoVo);
                return "system/main/index";
            } else {
                // 如果登陆失败跳转回登录页面，并且回显错误信息
                model.addAttribute("errorMsg", SysConstast.LOGININ_ERROR_MESSAGE);
                return "system/main/login";
            }
        } else {
            // 如果登录失败跳转回登录页面，并其回显验证码错误信息
            model.addAttribute("errorMsg", SysConstast.LOGIN_CODE_ERROR);
            return "system/main/login";
        }


    }

    /**
     * 得到登录的验证码
     */
    @RequestMapping(value = "/getCode")
    public void getCode(HttpSession session, HttpServletResponse response) {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36, 4, 5);
        session.setAttribute("code", lineCaptcha.getCode());
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(lineCaptcha.getImage(), "JPEG", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 该方法用于登出
     *
     * @return
     */
    @GetMapping(value = "/logout")
    public String logout() {
        WebUtils.getHttpSession().removeAttribute("user");
        return "system/main/login";
    }

    /**
     * 该方法用于查询所有的管理员信息，并且将其显示在页面上，使用分页，使用模糊查询
     *
     * @param userVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {
        DataGridView dataGridView = userService.loadAllUser(userVo);
        return dataGridView;
    }

    /**
     * 用于添加用户
     *
     * @param userVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addUser")
    public ResultObj addUser(UserVo userVo) {
        try {
            // 如果添加成功，返回添加成功
            userService.addUser(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            // 如果添加失败，返回添加失败
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 用于删除管理员
     *
     * @param userVo
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/delUser")
    public ResultObj delRole(UserVo userVo) {
        try {
            userService.delRole(userVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 用于修改管理员
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/updateUser")
    public ResultObj updateUser(UserVo userVo) {
        try {
            userService.updateUser(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
}
