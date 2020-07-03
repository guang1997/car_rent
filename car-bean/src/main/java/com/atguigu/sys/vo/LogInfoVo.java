package com.atguigu.sys.vo;

import com.atguigu.sys.bean.SysLogLogin;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class LogInfoVo extends SysLogLogin {
    private Integer page;
    private Integer limit;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private List<Integer> ids;
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

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "LogInfoVo{" +
                "page=" + page +
                ", limit=" + limit +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", ids=" + ids +
                "} " + super.toString();
    }
}
