package com.atguigu.sys.controller;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.ResultObj;
import com.atguigu.car.utils.WebUtils;
import com.atguigu.sys.bean.SysNews;
import com.atguigu.sys.bean.SysUser;
import com.atguigu.sys.service.NewsService;
import com.atguigu.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping(value = "/news")
@RestController
public class NewsController {
    @Autowired
    NewsService newsService;

    /**
     * 用于查看所有的公告
     */
    @RequestMapping(value = "/loadAllNews")
    public DataGridView loadAllNews(NewsVo newsVo) {

        DataGridView dataGridView = newsService.loadAllNews(newsVo);
//        List<SysNews> data = (List<SysNews>) dataGridView.getData();
//        for (SysNews datum : data) {
//            System.out.println(datum);
//        }
        return dataGridView;
    }

    /**
     * 用于添加公告
     * @param newsVo
     * @return
     */
    @RequestMapping(value = "/addNews")
    public ResultObj addNews(NewsVo newsVo) {
        try {
            newsVo.setCreatetime(new Date());
           SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute("user");
           newsVo.setOpername(user.getRealname());
            newsService.addNews(newsVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 用于删除公告
     * @param newsVo
     * @return
     */
    @RequestMapping(value = "/delNews")
    public ResultObj delNews(NewsVo newsVo) {
        try {
            newsService.delNews(newsVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 用于批量删除公告
     * @param newsVo
     * @return
     */
    @RequestMapping(value = "/deleteBatchNews")
    public ResultObj deleteBatchNews(NewsVo newsVo) {
        try {
            newsService.deleteBatchNews(newsVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 用于更新公告
     * @param newsVo
     * @return
     */
    @RequestMapping(value = "/updateNews")
    public ResultObj updateNews(NewsVo newsVo) {
        try {
            newsService.updateNews(newsVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据id查询公告
     * @param id
     * @return
     */
    @GetMapping(value = "/loadNewsById")
    public SysNews loadNewsById(Integer id) {
        System.out.println(id);
        return newsService.loadNewsById(id);
    }
}
