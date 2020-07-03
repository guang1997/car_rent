package com.atguigu.sys.service;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.sys.bean.SysNews;
import com.atguigu.sys.vo.NewsVo;

public interface NewsService {

    DataGridView loadAllNews(NewsVo newsVo);

    void addNews(NewsVo newsVo);

    void delNews(NewsVo newsVo);

    void deleteBatchNews(NewsVo newsVo);

    void updateNews(NewsVo newsVo);

    SysNews loadNewsById(Integer id);
}
