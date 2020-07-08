package com.atguigu.stat.service.impl;

import com.atguigu.stat.DataEntity;
import com.atguigu.stat.mapper.StatPieMapper;
import com.atguigu.stat.service.StatPieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatPieServiceImpl implements StatPieService {
    @Autowired
    StatPieMapper statPieMapper;
    /**
     * 用于查询用户地址，返回饼状图页面
     */
    @Override
    public List<DataEntity> getStatJson() {
       return statPieMapper.getStatJson();
    }


}
