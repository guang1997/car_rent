package com.atguigu.stat.controller;

import com.atguigu.stat.DataEntity;
import com.atguigu.stat.service.StatPieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "/stat")
@Controller
public class StatPieController {

    @Autowired
    StatPieService statPieService;

    /**
     * 用于查询用户来源地区数据，将数据返回到页面
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/getStatJson")
    public List<DataEntity> getStatJson() {

        return statPieService.getStatJson();
    }


    /**
     * 用于跳转到饼状图页面
     * @return
     */
    @RequestMapping(value = "/toCustomerPieStatPage")
    public String toCustomerPieStat() {
        return "stat/CustomerPieStat";
    }
}
