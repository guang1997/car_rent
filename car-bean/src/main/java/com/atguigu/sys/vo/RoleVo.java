package com.atguigu.sys.vo;

import com.atguigu.sys.bean.SysRole;

import java.util.Arrays;
import java.util.List;

public class RoleVo extends SysRole {
    // 分页参数
    private Integer page;
    private Integer limit;
    // 接收多个id
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

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "RoleVo{" +
                "page=" + page +
                ", limit=" + limit +
                ", ids=" + ids +
                "} " + super.toString();
    }
}
