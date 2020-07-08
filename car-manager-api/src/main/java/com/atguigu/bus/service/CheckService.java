package com.atguigu.bus.service;

import com.atguigu.bus.bean.BusCar;
import com.atguigu.bus.bean.BusChecks;
import com.atguigu.bus.bean.BusCustomer;
import com.atguigu.bus.bean.BusRent;
import com.atguigu.bus.vo.CheckVo;
import com.atguigu.bus.vo.RentVo;
import com.atguigu.car.utils.DataGridView;

public interface CheckService {
    BusChecks getCheckByRentId(String rentid);

    BusRent getRentById(String rentid);

    BusCar getCarByRentId(String rentid);

    BusCustomer getCustomerByRentId(String rentid);

    void saveCheck(CheckVo checkVo);

    DataGridView loadAllCheckList(CheckVo checkVo);

    void updateCheckList(CheckVo checkVo);
}
