package com.atguigu.sys.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SysNews {

    private Integer id;

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;

    private String opername;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOpername() {
        return opername;
    }

    public void setOpername(String opername) {
        this.opername = opername == null ? null : opername.trim();
    }

    public SysNews() {
    }

    public SysNews(Integer id, String title, String content, Date createtime, String opername) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createtime = createtime;
        this.opername = opername;
    }

    @Override
    public String toString() {
        return "SysNews{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", opername='" + opername + '\'' +
                '}';
    }
}