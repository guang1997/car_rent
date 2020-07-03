package com.atguigu.sys.controller;

import com.atguigu.car.utils.*;
import com.atguigu.sys.bean.SysMenu;
import com.atguigu.sys.bean.SysUser;
import com.atguigu.sys.service.MenuService;
import com.atguigu.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {


    @Autowired
    MenuService menuService;

    /**
     * 该方法用于显示菜单树
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllMenu")
    public List<TreeNode> getAllMenu(MenuVo menuVo) {
        // 获取用户信息
        SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute("user");
        // 用来装菜单树集合
        List<SysMenu> list = null;
        menuVo.setAvailable(SysConstast.AVAIABLE_TRUE);//只查询可用的
        // 如果用户是超级管理员
        if (user.getType().equals(SysConstast.USER_TYPE_SUPER)) {
            list = menuService.getAllMenu(menuVo);
        } else {
            // 如果是普通用户
            list = menuService.getAllMenuById(menuVo, user.getUserid());
        }
        // 遍历得到的集合，经数据存储到TreeNode中
        List<TreeNode> treeNodes1 = new ArrayList<>();
        for (SysMenu menu : list) {
            Boolean isSpead = menu.getSpread().equals(SysConstast.SPEEC_TRUE);
            treeNodes1.add(new TreeNode(menu.getId(), menu.getPid(), menu.getTitle(), menu.getIcon(), menu.getHref(), isSpead, menu.getTarget()));
        }
        return TreeNodeBuilder.getTreeNode(treeNodes1, SysConstast.USER_TYPE_SUPER);
    }

    /**
     * 该方法用于返回json字符串
     * @param menuVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo) {
        List<SysMenu> allMenu = menuService.getAllMenu(menuVo);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (SysMenu menu : allMenu) {
            Boolean isSpead = menu.getSpread().equals(SysConstast.SPEEC_TRUE);
            treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu.getTitle(), menu.getIcon(), menu.getHref(), isSpead, menu.getTarget()));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 该方法用于分页显示表格数据
     * @param menuVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loadAllMenu")
    public DataGridView loadAllMenu(MenuVo menuVo) {
        DataGridView dataGridView = menuService.queryAllMenu(menuVo);
        return dataGridView;
    }

    /**
     * 该方法用于根据左侧数据树来显示右侧表格中的数据
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loadAllMenuById")
    public DataGridView loadAllMenuById(Integer id) {
        DataGridView dataGridView = menuService.queryAllMenuById(id);
        return dataGridView;
    }

    /**
     * 该方法用于新增菜单
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addMenu")
    public String addMenu(MenuVo menuVo) {
        try {
            menuService.addMenu(menuVo);
        } catch (Exception e) {
//            e.printStackTrace();
            return SysConstast.ADD_ERROR;
        }
        return SysConstast.ADD_SUCCESS;
    }

    /**
     * 该方法用于修改菜单
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateMenu")
    public ResultObj updateMenu(MenuVo menuVo) {
        try {
            menuService.updateMenu(menuVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
        return ResultObj.UPDATE_SUCCESS;
    }

    /**
     * 该方法用于查询数据是否有子节点
     * @param menuVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(MenuVo menuVo) {
        Long count = menuService.checkMenuHasChildren(menuVo);
        if (count > 0) {
            // 如果有子节点，向页面发送0
            return  ResultObj.CODE_SUCCESS;
        } else {
            // 如果没有子节点，向页面发送-1
            return ResultObj.CODE_ERROR;
        }
    }

    /**
     * 该方法用于删除菜单
     * @param menuVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delMenuById")
    public ResultObj delMenuById(MenuVo menuVo) {
        try {
            menuService.delMenuById(menuVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
