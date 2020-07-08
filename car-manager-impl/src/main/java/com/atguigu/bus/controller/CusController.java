package com.atguigu.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/cus")
@Controller
public class CusController {

    @RequestMapping(value = "/toRentListManagerPage")
    public String toRentListManagerPage() {
        return "business/rent/rentListManager";
    }
    /**
     * 跳转到汽车出租页面
     * @return
     */
    @RequestMapping(value = "/toRentCarManager")
    public String toRentCarManager() {
        return "business/rent/rentCar";
    }
    /**
     * 跳转到客户管理页面
     * @return
     */
    @RequestMapping(value = "/toCustomerPage")
    public String toCustomerPage() {
        return "business/customer/customerManager";
    }


    /**
     * 跳转到车辆管理页面
     * @return
     */
    @RequestMapping(value = "/toCarManagerPage")
    public String toCarManager() {
        return "business/car/carManager";
    }
}
