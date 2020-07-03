package com.atguigu.sys.vo;

import com.atguigu.sys.bean.SysUser;

import java.util.List;

public class UserVo extends SysUser {
    // 分页参数
    private Integer page;
    private Integer limit;
    // 用于保存用户角色id
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "page=" + page +
                ", limit=" + limit +
                ", ids=" + ids +
                "} " + super.toString();
    }
}
