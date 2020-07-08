package com.atguigu.bus.service.impl;

import com.atguigu.bus.bean.*;
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

    /**
     * 用于查询显示所有的出租单
     * @param rentVo
     * @return
     */
    @Override
    public DataGridView loadAllRentList(RentVo rentVo) {
        Page<Object> pages = PageHelper.startPage(rentVo.getPage(), rentVo.getLimit());
        BusRentExample exa = new BusRentExample();
        BusRentExample.Criteria criteria = exa.createCriteria();
        // 对模糊查询进行条件判断
        if (!StringUtil.isEmpty(rentVo.getRentid())) {
            criteria.andRentidLike("%" + rentVo.getRentid() + "%");
        }
        if (!StringUtil.isEmpty(rentVo.getIdentity())) {
            criteria.andIdentityLike("%" + rentVo.getIdentity() + "%");
        }
        if (!StringUtil.isEmpty(rentVo.getCarnumber())) {
            criteria.andCarnumberLike("%" + rentVo.getCarnumber() + "%");
        }
        if (rentVo.getStartTime() !=null) {
            criteria.andBegindateGreaterThan(rentVo.getStartTime());
        }
        if (rentVo.getEndTime() !=null) {
            criteria.andReturndateLessThan(rentVo.getEndTime());
        }

        // 是否归还
        if (rentVo.getRentflag() != null) {
            if (rentVo.getRentflag() == 1) {
                criteria.andRentflagEqualTo(1);
            } else {
                criteria.andRentflagEqualTo(0);
            }
        }

        List<BusRent> rentList = busRentMapper.selectByExample(exa);
        for (BusRent busRent : rentList) {
            System.out.println(busRent);
        }
        return new DataGridView(pages.getTotal(), rentList);
    }

    /**
     * 用于删除出租单
     * @param rentVo
     */
    @Override
    public void delRentList(RentVo rentVo) {
        busRentMapper.deleteByPrimaryKey(rentVo.getRentid());
    }

    /**
     * 用于批量删除出租单
     * @param rentVo
     */
    @Override
    public void deleteBatch(RentVo rentVo) {
        BusRentExample exa = new BusRentExample();
        BusRentExample.Criteria criteria = exa.createCriteria();
        criteria.andRentidIn(rentVo.getIds());
        busRentMapper.deleteByExample(exa);
    }

    /**
     * 修改订单
     * @param rentVo
     */
    @Override
    public void updateRentList(RentVo rentVo) {
        busRentMapper.updateByPrimaryKeySelective(rentVo);
    }
}
