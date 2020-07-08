package com.atguigu.bus.controller;

import com.atguigu.bus.bean.BusCar;
import com.atguigu.bus.bean.BusChecks;
import com.atguigu.bus.bean.BusCustomer;
import com.atguigu.bus.bean.BusRent;
import com.atguigu.bus.service.CheckService;
import com.atguigu.bus.vo.CheckVo;
import com.atguigu.bus.vo.RentVo;
import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/check")
@RestController
public class CheckController {
    @Autowired
    CheckService checkService;

    /**
     * 用于更新检查单
     * @param checkVo
     * @return
     */
    @RequestMapping(value = "/updateCheckList")
    public ResultObj updateCheckList(CheckVo checkVo) {
        try {
            checkService.updateCheckList(checkVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 得到所有的检查单数据
     * @return
     */
    @RequestMapping(value = "/loadAllCheckList")
    public DataGridView loadAllCheckList(CheckVo checkVo) {
        return  checkService.loadAllCheckList(checkVo);
    }
    /**
     * 存储检查单
     * @param checkVo
     * @return
     */
    @PostMapping(value = "/saveCheck")
    public ResultObj saveCheck(CheckVo checkVo) {
        try {
            checkService.saveCheck(checkVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 根据rentid查询用户信息
     * @param rentid
     * @return
     */
    @PostMapping(value = "/getCustomerByRentId")
    public BusCustomer getCustomerByRentId(String rentid) {
        return checkService.getCustomerByRentId(rentid);
    }
    /**
     * 根据id查询出租单信息
     * @param rentid
     * @return
     */
    @PostMapping(value = "/getRentById")
    public BusRent getRentById(String rentid) {
        return checkService.getRentById(rentid);
    }

    /**
     * 根据rentid查询车辆信息
     * @param rentid
     * @return
     */
    @PostMapping(value = "/getCarByRentId")
    public BusCar getCarByRentId(String rentid) {
        return checkService.getCarByRentId(rentid);
    }
    /**
     *  查询检查单信息
     * @param rentid
     * @return
     */
    @PostMapping(value = "/getCheckByRentId")
    public BusChecks getCheckByRentId(String rentid) {
        return  checkService.getCheckByRentId(rentid);
    }
}
