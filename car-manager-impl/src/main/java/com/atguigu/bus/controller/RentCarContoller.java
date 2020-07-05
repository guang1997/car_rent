package com.atguigu.bus.controller;

import com.atguigu.bus.bean.BusCustomer;
import com.atguigu.bus.service.CustomerService;
import com.atguigu.bus.service.RentCarService;
import com.atguigu.bus.vo.RentVo;
import com.atguigu.car.utils.*;
import com.atguigu.sys.bean.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping(value = "/rent")
@RestController
public class RentCarContoller {


    @Autowired
    RentCarService rentCarService;
    @Autowired
    CustomerService customerService;


    @RequestMapping(value = "/addRentCar")
    public ResultObj addRentCar(RentVo rentVo) {
        try {
            rentCarService.addCarRent(rentVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 该方法用于回显出租信息
     * @param rentVo
     * @return
     */
    @GetMapping(value = "/initRentTable")
    public RentVo initRentTable(RentVo rentVo) {
        // 设置订单号
        rentVo.setRentid(RandomUtils.createRandomStringUserTime(SysConstast.CAR_ORDER_CZ));
        // 设置起租时间
        rentVo.setBegindate(new Date());
        // 设置操作员
        SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute("user");
        rentVo.setOpername(user.getRealname());
        return rentVo;
    }
    /**
     * 根据身份证号查询用户是否存在，使用模糊查询
     * @return
     */
    @RequestMapping(value = "/checkRentCar")
    public ResultObj checkRentCar(String identity) {
       BusCustomer customer = customerService.queryCustomerById(identity);
        if (null != customer) {

            return ResultObj.CODE_SUCCESS;
        } else {
            return ResultObj.CODE_ERROR;
        }
    }
}
