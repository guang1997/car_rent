package com.atguigu.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/main")
@Controller
public class DeskManagerController {

    /**
     * 该方法用于跳转到main页面
     * @return
     */
    @RequestMapping(value = "/toDeskManagerPage")
    public String toDeskManagerPage() {
        return "system/main/main";
    }
}
