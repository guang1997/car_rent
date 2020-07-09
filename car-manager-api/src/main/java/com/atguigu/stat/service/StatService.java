package com.atguigu.stat.service;

import com.atguigu.stat.DataEntity;

import java.util.List;

public interface StatService {
    List<DataEntity> getStatJson();

    List<DataEntity> getEmpSalesJson(String year);

    List<Double> getComSaleLineJson(String year);
}
