package com.atguigu.sys.service.impl;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.MD5Util;
import com.atguigu.car.utils.StringUtil;
import com.atguigu.car.utils.SysConstast;
import com.atguigu.sys.bean.SysRole;
import com.atguigu.sys.bean.SysUser;
import com.atguigu.sys.bean.SysUserExample;
import com.atguigu.sys.mapper.SysRoleMapper;
import com.atguigu.sys.mapper.SysUserMapper;
import com.atguigu.sys.service.SysUserService;
import com.atguigu.sys.vo.UserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.mail.imap.protocol.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper userMapper;

    /**
     * 该方法用于验证登陆
     *
     * @param userVo
     */
    @Override
    public SysUser doLogin(UserVo userVo) {
        // 查找数据库中是否有对应的数据
        SysUserExample exa = new SysUserExample();
        exa.createCriteria().andLoginnameEqualTo(userVo.getLoginname()).andPwdEqualTo(MD5Util.digest(userVo.getPwd()));
        List<SysUser> sysUsers = userMapper.selectByExample(exa);
        // 如果没有对应的数据，返回空
        if (CollectionUtils.isEmpty(sysUsers) || sysUsers.size() > 1) {
            return null;
        }
        // 如果有对应的数据，返回对应的数据
            return sysUsers.get(0);
    }

    /**
     * 该方法用于查询所有的用户信息，使用分页，使用模糊查询
     *
     * @param userVo
     * @return
     */
    @Override
    public DataGridView loadAllUser(UserVo userVo) {
        // 用于分页
        Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        SysUserExample exa = new SysUserExample();
        SysUserExample.Criteria criteria = exa.createCriteria();
        // 模糊查询条件开始
        // 登录名
        if (!StringUtil.isEmpty(userVo.getLoginname())) {
            criteria.andLoginnameLike("%" + userVo.getLoginname() + "%");
        }
        // 真实姓名
        if (!StringUtil.isEmpty(userVo.getRealname())) {
            criteria.andRealnameLike("%" + userVo.getRealname() + "%");
        }
        // 身份证号
        if (!StringUtil.isEmpty(userVo.getIdentity())) {
            criteria.andIdentityLike("%" + userVo.getIdentity() + "%");
        }
        // 住址
        if (!StringUtil.isEmpty(userVo.getAddress())) {
            criteria.andAddressLike("%" + userVo.getAddress() + "%");
        }
        // 性别
        if (userVo.getSex() != null) {
            if (userVo.getSex() == 1) {
                criteria.andSexEqualTo(1);
            } else {
                criteria.andSexEqualTo(0);
            }
        }

        // 电话
        if (!StringUtil.isEmpty(userVo.getPhone())) {
            criteria.andPhoneEqualTo(userVo.getPhone());
        }
        // 模糊查询条件结束
        List<SysUser> users = userMapper.selectByExample(exa);
//        System.out.println(users);
        return new DataGridView(page.getTotal(), users);
    }

    /**
     * 用于天机用户
     * @param userVo
     */
    @Override
    public void addUser(UserVo userVo) {
        // 对密码进行加密
        userVo.setPwd(MD5Util.digest(userVo.getPwd()));

        userVo.setType(2);
        userMapper.insertSelective(userVo);
    }

    /**
     * 用于删除管理员
     * @param userVo
     */
    @Override
    public void delRole(UserVo userVo) {
        userMapper.deleteByPrimaryKey(userVo.getUserid());
    }

    /**
     * 用于修改管理员信息
     * @param userVo
     */
    @Override
    public void updateUser(UserVo userVo) {
        userMapper.updateByPrimaryKeySelective(userVo);
    }

    @Autowired
    SysRoleMapper roleMapper;
    /**
     * 用于初始化用户角色表格
     * @param userVo
     * @return
     */
    @Override
    public DataGridView initUserRoleTable(UserVo userVo) {
        // 查询到所有的角色
        List<SysRole> allRoles = roleMapper.selectByExample(null);
        // 查询用户已经拥有的角色
        Page<Object> pages = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        List<SysRole> roles = userMapper.selectUserRolesByUserId(userVo.getUserid());
        // 用于存储用户信息集合
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        // 由于前台页面需要LAYTABLE_TIPS参数来进行表格的选中，所以定义一个LAYTABLE_TIPS传给前台
        for (SysRole r1 : allRoles) {
            Boolean LAY_CHECKED = false;
            for (SysRole r2 : roles) {
                // 如果角色id等于用户拥有的角色id，设置LAYTABLE_TIPS为true
                if (r1.getRoleid()==r2.getRoleid()) {
                    LAY_CHECKED=true;
                }
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("roleid", r1.getRoleid());
            map.put("rolename", r1.getRolename());
            map.put("roledesc", r1.getRoledesc());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(pages.getTotal(), data);
    }

    /**
     * 用于重置用户密码
     * @param userid
     */
    @Override
    public void changeUserPwd(Integer userid) {
        SysUser user = new SysUser();
        user.setUserid(userid);
        user.setPwd(MD5Util.digest(SysConstast.USER_DEFAULT_PWD));
        userMapper.updateByPrimaryKeySelective(user);
    }

}
