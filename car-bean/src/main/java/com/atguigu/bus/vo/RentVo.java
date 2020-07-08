package com.atguigu.bus.vo;

import com.atguigu.bus.bean.BusRent;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class RentVo extends BusRent {

    private Integer page;
    private Integer limit;

    // 用于批量删除
    private List<String> ids;
    // 用于接收前台传过来的查询数据
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "RentVo{" +
                "page=" + page +
                ", limit=" + limit +
                ", ids=" + ids +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
