package com.atguigu.sys.service.impl;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.sys.bean.SysNews;
import com.atguigu.sys.bean.SysNewsExample;
import com.atguigu.sys.mapper.SysNewsMapper;
import com.atguigu.sys.service.NewsService;
import com.atguigu.sys.vo.NewsVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    SysNewsMapper newsMapper;

    /**
     * 用于返回所有的公告
     * @param newsVo
     * @return
     */
    @Override
    public DataGridView loadAllNews(NewsVo newsVo) {
        Page<Object> pages = PageHelper.startPage(newsVo.getPage(), newsVo.getLimit());
        SysNewsExample exa = new SysNewsExample();
        SysNewsExample.Criteria criteria = exa.createCriteria();
        if (!StringUtils.isEmpty(newsVo.getTitle())) {
            criteria.andTitleLike("%" + newsVo.getTitle() + "%");
        }
        if (!StringUtils.isEmpty(newsVo.getContent())) {
            criteria.andContentLike("%" + newsVo.getContent() + "%");
        }
        if (!StringUtils.isEmpty(newsVo.getStartTime())) {
            criteria.andCreatetimeGreaterThan(newsVo.getCreatetime());
        }
        if (!StringUtils.isEmpty(newsVo.getEndTime())) {
            criteria.andCreatetimeLessThan(newsVo.getEndTime());
        }
        if (!StringUtils.isEmpty(newsVo.getOpername())) {
            criteria.andOpernameLike("%" + newsVo.getOpername() + "%");
        }
        List<SysNews> news = newsMapper.selectByExample(exa);
        return new DataGridView(pages.getTotal(), news);
    }

    /**
     * 用于添加公告
     * @param newsVo
     */
    @Override
    public void addNews(NewsVo newsVo) {
        newsMapper.insertSelective(newsVo);
    }

    /**
     * 用于删除公告
     * @param newsVo
     */
    @Override
    public void delNews(NewsVo newsVo) {
        newsMapper.deleteByPrimaryKey(newsVo.getId());
    }

    /**
     * 用于批量删除公告
     * @param newsVo
     */
    @Override
    public void deleteBatchNews(NewsVo newsVo) {
        SysNewsExample exa = new SysNewsExample();
        SysNewsExample.Criteria criteria = exa.createCriteria();
        criteria.andIdIn(newsVo.getIds());
        newsMapper.deleteByExample(exa);
    }

    /**
     * 用于更新公告
     * @param newsVo
     */
    @Override
    public void updateNews(NewsVo newsVo) {
        newsMapper.updateByPrimaryKeySelective(newsVo);
    }

    /**
     * 根据id查看公告
     * @param id
     * @return
     */
    @Override
    public SysNews loadNewsById(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }
}
