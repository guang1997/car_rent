package com.atguigu.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 该类用于跳转页面
 */
@Controller
@RequestMapping(value = "/sys")
public class SysController {

    @RequestMapping(value = "/toNewsPage")
    public String toNewsPage() {
        return "system/news/news";
    }
    /**
     * 跳转到日志管理页面
     * @return
     */
    @RequestMapping(value = "/toLogInfoPage")
    public String toLogInfoPage() {
        return "system/logInfo/logInfo";
    }
    /**
     * 跳转到菜单管理页面
     * @return
     */
    @RequestMapping(value = "/toMenuManagerPage")
    public String toMenuManagerPage() {

        return "system/menus/menuManager";
    }

    /**
     * 跳转到菜单管理页面左侧
     * @return
     */
    @RequestMapping(value = "/toMenuLeft")
    public String toMenuLeft() {

        return "system/menus/menuLeft";
    }

    /**
     * 跳转到菜单页面右侧
     * @return
     */
    @RequestMapping(value = "/toMenuRight")
    public String toMenuRight() {

        return "system/menus/nenuRight";
    }

    /**
     * 跳转到角色管理页面
     * @return
     */
    @RequestMapping(value = "/toRoleManagerPage")
    public String toRoleManager() {

        return "system/role/roleManager";
    }
    /**
     * 跳转到角色管理页面
     * @return
     */
    @RequestMapping(value = "/toUserManagerPage")
    public String toUserManager() {

        return "system/user/userManager";
    }
}
