package com.atguigu.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DispatcherController {
    @RequestMapping(value = {"/index" , "/" ,"/index.html"})
    public String toIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/code")
    public String toCodePage() {
        return "code";
    }
}
