package com.atguigu.sys.controller;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.ResultObj;
import com.atguigu.sys.bean.SysRole;
import com.atguigu.sys.service.RoleService;
import com.atguigu.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "/role")
@Controller
public class RoleController {

    @Autowired
    RoleService roleService;
    /**
     * 用于加载角色
     * @param roleVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo) {
        DataGridView dataGridView = roleService.loadAllRole(roleVo);
        return dataGridView;
    }

    /**
     * 用于添加角色
     * @param roleVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addRole")
    public ResultObj addRole(RoleVo roleVo) {
        try {
            roleService.addRole(roleVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 用于删除角色
     * @param roleVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delRole")
    public ResultObj delRole(RoleVo roleVo) {
        try {
            roleService.delRole(roleVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 用于批量删除角色
     * @param roleVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteBatchRole")
    public ResultObj deleteBatchRole(RoleVo roleVo) {
        try {
            roleService.deleteBatchRole(roleVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 用于更改角色信息
     * @param roleVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateRole")
    public ResultObj updateRole(RoleVo roleVo) {
        try {
            roleService.updateRole(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 用于加载权限树
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/initRoleMenuTreeJson")
    public DataGridView initRoleMenuTreeJson(Integer roleId) {
       DataGridView dataGridView = roleService.initRoleMenuTreeJson(roleId);
        return dataGridView;
    }

    /**
     * 用于保存角色和菜单的关系
     * @param roleVo
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/saveRoleMenu")
    public ResultObj saveRoleMenu(RoleVo roleVo) {
        try {
            roleService.saveRoleMenu(roleVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
}
