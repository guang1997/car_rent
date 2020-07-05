package com.atguigu.bus.service;

import com.atguigu.bus.bean.BusCar;
import com.atguigu.bus.vo.CarVo;
import com.atguigu.car.utils.DataGridView;

public interface CarService {

    public DataGridView queryAllCar(CarVo carVo);

    public void delCar(CarVo carVo);

    public void batchDelCar(CarVo carVo);

    public void addCar(CarVo carVo);

    public void updateCar(CarVo carVo);

    BusCar selectCarByCarNum(String carnumber);
}
