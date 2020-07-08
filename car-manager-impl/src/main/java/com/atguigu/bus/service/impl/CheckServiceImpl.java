package com.atguigu.bus.service.impl;

import com.atguigu.bus.bean.*;
import com.atguigu.bus.mapper.BusCarMapper;
import com.atguigu.bus.mapper.BusChecksMapper;
import com.atguigu.bus.mapper.BusCustomerMapper;
import com.atguigu.bus.mapper.BusRentMapper;
import com.atguigu.bus.service.CheckService;
import com.atguigu.bus.vo.CheckVo;
import com.atguigu.bus.vo.RentVo;
import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.RandomUtils;
import com.atguigu.car.utils.StringUtil;
import com.atguigu.car.utils.SysConstast;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class CheckServiceImpl implements CheckService {
    @Autowired
    BusChecksMapper checkMapper;

    @Autowired
    BusRentMapper rentMapper;
    
    @Autowired
    BusCarMapper carMapper;

    @Autowired
    BusCustomerMapper customerMapper;
    /**
     * 用于查询检查单信息
     * @param rentid
     * @return
     */
    @Override
    public BusChecks getCheckByRentId(String rentid) {
        BusChecksExample exa = new BusChecksExample();
        exa.createCriteria().andRentidEqualTo(rentid);
        List<BusChecks> checks = checkMapper.selectByExample(exa);
        // 如果没有查询到相应的数据，需要对数据进行封装
        if (CollectionUtils.isEmpty(checks)) {
            // 根据rentid去rent表中查询对应数据
            BusRent rent = getRentById(rentid);
            BusChecks check = new BusChecks();
            check.setRentid(rentid);
            check.setCheckdate(new Date());
            check.setCreatetime(rent.getCreatetime());
            check.setOpername(rent.getOpername());
            check.setCheckid(RandomUtils.createRandomStringCheckId(SysConstast.CAR_ORDER_JC));
            return check;
        } else {
            return checks.get(0);
        }
    }

    /**
     * 根据id查询出租单信息
     * @param rentid
     * @return
     */
    @Override
    public BusRent getRentById(String rentid) {
        return  rentMapper.selectByPrimaryKey(rentid);
    }

    /**
     * 根据rentid查询车辆信息
     * @param rentid
     * @return
     */
    @Override
    public BusCar getCarByRentId(String rentid) {
        return carMapper.getCarByRentId(rentid);
    }

    /**
     * 根据rentid查询用户信息
     * @param rentid
     * @return
     */
    @Override
    public BusCustomer getCustomerByRentId(String rentid) {
        return customerMapper.getCustomerByRentId(rentid);
    }

    /**
     * 用于保存检查单
     * @param checkVo
     */
    @Override
    public void saveCheck(CheckVo checkVo) {
        checkVo.setCreatetime(new Date());
        // 更改车辆信息
        BusCar car = carMapper.getCarByRentId(checkVo.getRentid());
        car.setIsrenting(SysConstast.CAR_BACK_TRUE);
        carMapper.updateByPrimaryKeySelective(car);
        // 更改出租单信息
        BusRent rent = rentMapper.selectByPrimaryKey(checkVo.getRentid());
        rent.setRentflag(SysConstast.RENT_BACK_FALSE);
        rentMapper.updateByPrimaryKeySelective(rent);
        checkMapper.insertSelective(checkVo);
    }

    /**
     * 得到所有的检查单数据
     * @param checkVo
     * @return
     */
    @Override
    public DataGridView loadAllCheckList(CheckVo checkVo) {
        Page<Object> pages = PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
        BusChecksExample exa = new BusChecksExample();
        BusChecksExample.Criteria criteria = exa.createCriteria();
        if (!StringUtil.isEmpty(checkVo.getCheckid())) {
            criteria.andCheckidLike("%" + checkVo.getCheckid() + "%");
        }
        if (!StringUtil.isEmpty(checkVo.getRentid())) {
            criteria.andRentidLike("%" + checkVo.getRentid() + "%");
        }
        if (!StringUtil.isEmpty(checkVo.getProblem())) {
            criteria.andProblemLike("%" + checkVo.getProblem() + "%");
        }
        if (checkVo.getStartTime() != null) {
            criteria.andCreatetimeGreaterThan(checkVo.getStartTime());
        }
        if (checkVo.getEndTime() != null) {
            criteria.andCreatetimeLessThan(checkVo.getStartTime());
        }
        if (!StringUtil.isEmpty(checkVo.getCheckdesc())) {
            criteria.andCheckdescLike("%" + checkVo.getCheckdesc() + "%");
        }
        List<BusChecks> check = checkMapper.selectByExample(exa);
        return new DataGridView(pages.getTotal(), check);
    }

    /**
     * 用于更新检查单
     * @param checkVo
     */
    @Override
    public void updateCheckList(CheckVo checkVo) {
        checkMapper.updateByPrimaryKeySelective(checkVo);
    }
}
