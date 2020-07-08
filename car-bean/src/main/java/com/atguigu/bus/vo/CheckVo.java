package com.atguigu.bus.vo;

import com.atguigu.bus.bean.BusChecks;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class CheckVo extends BusChecks {

    private Integer page;
    private Integer limit;
    // 用于接收前台传过来的查询数据
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private List<String> ids;
    public CheckVo() {
    }

    public CheckVo(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

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

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
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

    @Override
    public String toString() {
        return "CheckVo{" +
                "page=" + page +
                ", limit=" + limit +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", ids=" + ids +
                '}';
    }
}
