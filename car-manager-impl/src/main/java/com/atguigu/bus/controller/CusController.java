package com.atguigu.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/cus")
@Controller
public class CusController {

    /**
     * 跳转到检查单管理页面
     * @return
     */
    @RequestMapping(value = "/toCheckListPage")
    public String toCheckListPage() {
        return "business/check/checkListManager";
    }
    /**
     * 跳转到出租单检查页面
     * @return
     */
    @RequestMapping(value = "/toCheckCarManagerPage")
    public String toCheckCarManagerPage() {
        return "business/check/checkCarManager";
    }
    /**
     * 跳转到出租单管理页面
     * @return
     */
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
