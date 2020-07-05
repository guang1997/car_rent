package com.atguigu.bus.service.impl;

import com.atguigu.bus.bean.BusCar;
import com.atguigu.bus.bean.BusCarExample;
import com.atguigu.bus.mapper.BusCarMapper;
import com.atguigu.bus.service.CarService;
import com.atguigu.bus.vo.CarVo;
import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    BusCarMapper carMapper;

    /**
     * 查询显示所有车辆信息
     * @param carVo
     * @return
     */
    @Override
    public DataGridView queryAllCar(CarVo carVo) {

        Page<Object> pages = PageHelper.startPage(carVo.getPage(), carVo.getLimit());
        BusCarExample exa = new BusCarExample();
        BusCarExample.Criteria criteria = exa.createCriteria();

        if (!StringUtil.isEmpty(carVo.getCarnumber())) {
            criteria.andCarnumberLike("%" + carVo.getCarnumber() + "%");
        }
        if (!StringUtil.isEmpty(carVo.getCartype())) {
            criteria.andCartypeLike("%" + carVo.getCartype() + "%");
        }
        if (!StringUtil.isEmpty(carVo.getColor())) {
            criteria.andColorLike("%" + carVo.getColor() + "%");
        }
        // 根据售卖价格查询
        if (carVo.getLowPrice() != null && carVo.getLowPrice() >= 0) {
            criteria.andPriceGreaterThan(carVo.getLowPrice());
        }
        if (carVo.getHighPrice() != null && carVo.getHighPrice() >= 0) {
            criteria.andPriceLessThan(carVo.getHighPrice());
        }
        // 根据出租价格查询
        if (carVo.getLowRentPrice() != null && carVo.getLowRentPrice() >= 0) {
            criteria.andRentpriceGreaterThan(carVo.getLowRentPrice());
        }
        if (carVo.getHighRentPrice() != null && carVo.getHighRentPrice() >= 0) {
            criteria.andRentpriceLessThan(carVo.getHighRentPrice());
        }
        if (carVo.getIsrenting() != null) {
            if (carVo.getIsrenting() == 1){
                criteria.andIsrentingEqualTo(1);
            } else {
                criteria.andIsrentingEqualTo(0);
            }
        }
        if (!StringUtil.isEmpty(carVo.getDescription())) {
            criteria.andDescriptionLike("%" + carVo.getDescription() + "%");
        }
        List<BusCar> cars = carMapper.selectByExample(exa);
        return new DataGridView(pages.getTotal(), cars);
    }

    /**
     * 删除车辆
     * @param carVo
     */
    @Override
    public void delCar(CarVo carVo) {
        carMapper.deleteByPrimaryKey(carVo.getCarnumber());
    }

    /**
     * 批零删除车辆
     * @param carVo
     */
    @Override
    public void batchDelCar(CarVo carVo) {
        BusCarExample exa = new BusCarExample();
        BusCarExample.Criteria criteria = exa.createCriteria();
        criteria.andCarnumberIn(carVo.getIds());
        carMapper.deleteByExample(exa);
    }

    /**
     * 添加车辆
     * @param carVo
     */
    @Override
    public void addCar(CarVo carVo) {
        carMapper.insertSelective(carVo);
    }

    /**
     * 更新车辆信息
     * @param carVo
     */
    @Override
    public void updateCar(CarVo carVo) {
        carMapper.updateByPrimaryKeySelective(carVo);
    }

    /**
     * 根据车牌号查找车辆信息
     * @param carnumber
     */
    @Override
    public BusCar selectCarByCarNum(String carnumber) {
        return carMapper.selectByPrimaryKey(carnumber);
    }
}
