package com.atguigu.bus.vo;

import com.atguigu.bus.bean.BusRent;

public class RentVo extends BusRent {

    private Integer page;
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "RentVo{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
