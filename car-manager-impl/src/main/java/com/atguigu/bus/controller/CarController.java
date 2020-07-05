package com.atguigu.bus.controller;

import com.atguigu.bus.bean.BusCar;
import com.atguigu.bus.service.CarService;
import com.atguigu.bus.vo.CarVo;
import com.atguigu.car.utils.AppFileUtils;
import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.ResultObj;
import com.atguigu.car.utils.SysConstast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping(value = "/car")
@RestController
public class CarController {

    @Autowired
    CarService carService;

    /**
     * 查询显示所有的车辆
     * @param carVo
     * @return
     */
    @RequestMapping(value = "/loadAllCar")
    public DataGridView loadAllCar(CarVo carVo) {

        return carService.queryAllCar(carVo);
    }

    /**
     * 用于添加车辆信息
     * @return
     */
    @PostMapping(value = "/addCar")
    public ResultObj addCar(CarVo carVo) {
        try {
            //如果不是默认图片就去掉图片的_temp的后缀
            if(!carVo.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)) {
                String filePath= AppFileUtils.updateFileName(carVo.getCarimg(),SysConstast.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
            }

            carVo.setCreatetime(new Date());
            carService.addCar(carVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 用于更新车辆信息
     * @param carVo
     * @return
     */
    @PostMapping(value = "/updateCar")
    public ResultObj updateCar(CarVo carVo) {
        try {
            carService.updateCar(carVo);
            // 更换图片的操作
            // 如果传送过来的图片是以_temp结尾
            if (carVo.getCarimg().endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
                // 将图片结尾改为.jpg
                String fileName = AppFileUtils.updateFileName(carVo.getCarimg(), SysConstast.FILE_UPLOAD_TEMP);
                // 将得到的img的值传给carVo
                carVo.setCarimg(fileName);
                // 将数据库中存储的图片从硬盘上删掉
                BusCar car = carService.selectCarByCarNum(carVo.getCarnumber());
                AppFileUtils.removeFileByPath(car.getCarimg());
            }
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 用于批量删除车辆信息
     * @return
     */
    @PostMapping(value = "/deleteBatchCar")
    public ResultObj deleteBatchCar(CarVo carVo) {
        try {
            carService.batchDelCar(carVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 用于删除车辆信息
     * @param carVo
     * @return
     */
    @PostMapping(value = "/delCar")
    public ResultObj delCar(CarVo carVo) {
        try {
            carService.delCar(carVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
