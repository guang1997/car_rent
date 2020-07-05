package com.atguigu.bus.service.impl;

import com.atguigu.bus.bean.BusCar;
import com.atguigu.bus.bean.BusRent;
import com.atguigu.bus.bean.BusRentExample;
import com.atguigu.bus.mapper.BusCarMapper;
import com.atguigu.bus.mapper.BusRentMapper;
import com.atguigu.bus.service.RentCarService;
import com.atguigu.bus.vo.RentVo;
import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.StringUtil;
import com.atguigu.car.utils.SysConstast;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RentCarServiceImpl implements RentCarService {
    @Autowired
    BusRentMapper busRentMapper;

    @Autowired
    BusCarMapper carMapper;
    /**
     * 该方法用于新增出租单信息
     * @param rentVo
     */
    @Override
    public void addCarRent(RentVo rentVo) {
        // 创建时间
        rentVo.setCreatetime(new Date());
        // 归还信息
        rentVo.setRentflag(SysConstast.CAR_BACK_FALSE);
        // 更改车辆出租状态
        BusCar car = new BusCar();
        car.setCarnumber(rentVo.getCarnumber());
        car.setIsrenting(SysConstast.CAR_RENT_TRUE);
        carMapper.updateByPrimaryKeySelective(car);
        busRentMapper.insertSelective(rentVo);
    }
}
