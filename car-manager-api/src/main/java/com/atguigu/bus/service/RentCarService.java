package com.atguigu.bus.service;

import com.atguigu.bus.bean.BusRent;
import com.atguigu.bus.vo.RentVo;
import com.atguigu.car.utils.DataGridView;

public interface RentCarService {

    void addCarRent(RentVo rentVo);

    DataGridView loadAllRentList(RentVo rentVo);

    void delRentList(RentVo rentVo);

    void deleteBatch(RentVo rentVo);

    void updateRentList(RentVo rentVo);

    BusRent checkRentExist(String rentid);
}
