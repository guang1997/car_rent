package com.atguigu.sys.vo;

import com.atguigu.sys.bean.SysMenu;

public class MenuVo extends SysMenu {

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

    public void setLimit(Integer pageSize) {
        this.limit = pageSize;
    }

    @Override
    public String toString() {
        return "MenuVo{" +
                "page=" + page +
                ", limit=" + limit +
                "} " + super.toString();
    }
}
