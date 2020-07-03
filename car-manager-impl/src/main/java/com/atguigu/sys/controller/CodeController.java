package com.atguigu.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

@RequestMapping(value = "/code")
@Controller
public class CodeController {

    private Jedis jedis = new Jedis("192.168.237.129", 6379);
    private String phone;
    @ResponseBody
    @PostMapping(value = "/getcode")
    public String getcode(String phone) {
        this.phone = phone;
        int code = (int)(Math.random() * (999999-100000 + 1) + 100000);
        jedis.setex(phone, 120, code + "");
        jedis.setex(phone + "times", 120, "3");
        return code + "";
    }

    @RequestMapping(value = "/confirmCode")
    public String confirmCode(String code, Model model) {
        String codeFromRedis = jedis.get(phone+"");
        if (codeFromRedis.equals(code)) {
            return "ok";
        } else {
            int times = Integer.parseInt(jedis.get(phone+"times"));
            if (times <= 0) {
                model.addAttribute("errorMsg", "您已没有登录次数");
                return "code";
            }
            jedis.decr(phone + "times");
            model.addAttribute("errorMsg", "验证失败，请再试一次");
            return "code";
        }

    }
}
