package com.atguigu.stat.service.impl;

import com.atguigu.stat.DataEntity;
import com.atguigu.stat.mapper.StatMapper;
import com.atguigu.stat.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatServiceImpl implements StatService {
    @Autowired
    StatMapper statPieMapper;
    /**
     * 用于查询用户地址
     */
    @Override
    public List<DataEntity> getStatJson() {
       return statPieMapper.getStatJson();
    }

    /**
     * 用于查询员工销售额
     * @param year
     * @return
     */
    @Override
    public List<DataEntity> getEmpSalesJson(String year) {
        return statPieMapper.getEmpSalesJson(year);
    }

    /**
     * 用于查询公司年度销售额
     * @param year
     * @return
     */
    @Override
    public List<Double> getComSaleLineJson(String year) {
        return statPieMapper.getComSaleLineJson(year);
    }


}
