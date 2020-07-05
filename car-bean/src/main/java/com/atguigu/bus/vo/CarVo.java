package com.atguigu.bus.vo;

import com.atguigu.bus.bean.BusCar;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class CarVo extends BusCar {

    private Integer page;
    private Integer limit;
    private Double lowPrice;// 查询售卖价格区间
    private Double highPrice;// 查询售卖价格区间
    private Double lowRentPrice;// 查询出租价格区间
    private Double highRentPrice;// 查询出租价格区间
    private List<String> ids;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer pageSize) {
        this.limit = pageSize;
    }


    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowRentPrice() {
        return lowRentPrice;
    }

    public void setLowRentPrice(Double lowRentPrice) {
        this.lowRentPrice = lowRentPrice;
    }

    public Double getHighRentPrice() {
        return highRentPrice;
    }

    public void setHighRentPrice(Double highRentPrice) {
        this.highRentPrice = highRentPrice;
    }

    @Override
    public String toString() {
        return "CarVo{" +
                "page=" + page +
                ", limit=" + limit +
                ", lowPrice=" + lowPrice +
                ", highPrice=" + highPrice +
                ", lowRentPrice=" + lowRentPrice +
                ", highRentPrice=" + highRentPrice +
                ", ids=" + ids +
                '}';
    }
}
