package com.atguigu.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/cus")
@Controller
public class CusController {

    /**
     * 跳转到汽车出租页面
     * @return
     */
    @RequestMapping(value = "/toRentCarManager")
    public String toRentCarManager() {
        return "system/car/rentCar";
    }
    /**
     * 跳转到客户管理页面
     * @return
     */
    @RequestMapping(value = "/toCustomerPage")
    public String toCustomerPage() {
        return "system/customer/customerManager";
    }


    /**
     * 跳转到车辆管理页面
     * @return
     */
    @RequestMapping(value = "/toCarManagerPage")
    public String toCarManager() {
        return "system/car/carManager";
    }
}
