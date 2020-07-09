package com.atguigu.stat.mapper;

import com.atguigu.stat.DataEntity;

import java.util.List;

public interface StatMapper {


    List<DataEntity> getStatJson();

    List<DataEntity> getEmpSalesJson(String year);

    List<Double> getComSaleLineJson(String year);
}
