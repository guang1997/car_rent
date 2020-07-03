package com.atguigu.sys.service.impl;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.sys.bean.SysLogLogin;
import com.atguigu.sys.bean.SysLogLoginExample;
import com.atguigu.sys.mapper.SysLogLoginMapper;
import com.atguigu.sys.service.LogInfoService;
import com.atguigu.sys.vo.LogInfoVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Autowired
    SysLogLoginMapper loginMapper;

    /**
     * 用于删除日志信息
     * @param logInfoVo
     */
    @Override
    public void dellogInfo(LogInfoVo logInfoVo) {
        loginMapper.deleteByPrimaryKey(logInfoVo.getId());
    }

    /**
     * 用于显示日志信息
     * @param logInfoVo
     * @return
     */
    @Override
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
        Page<Object> pages = PageHelper.startPage(logInfoVo.getPage(), logInfoVo.getLimit());
        SysLogLoginExample exa = new SysLogLoginExample();
        SysLogLoginExample.Criteria criteria = exa.createCriteria();
        // 如果登录用户信息不为空，再进行验证
        if (!StringUtils.isEmpty(logInfoVo.getLoginname())) {
            criteria.andLoginnameLike("%" + logInfoVo.getLoginname() + "%");
        }

        if (!StringUtils.isEmpty(logInfoVo.getStartTime())){
            criteria.andLogintimeGreaterThan(logInfoVo.getStartTime());
        }
        if (!StringUtils.isEmpty(logInfoVo.getEndTime())){
            criteria.andLogintimeLessThan(logInfoVo.getEndTime());
        }
        if (!StringUtils.isEmpty(logInfoVo.getLoginip())){
            criteria.andLoginipEqualTo(logInfoVo.getLoginip());
        }
        List<SysLogLogin> logInfos = loginMapper.selectByExample(exa);
        return new DataGridView(pages.getTotal(), logInfos);
    }

    /**
     * 用于批量删除日志信息
     * @param logInfoVo
     */
    @Override
    public void deleteBatchLogInfo(LogInfoVo logInfoVo) {
        SysLogLoginExample exa = new SysLogLoginExample();
        exa.createCriteria().andIdIn(logInfoVo.getIds());
        loginMapper.deleteByExample(exa);
    }

    /**
     * 用于添加登录日志信息
     * @param logInfoVo
     */
    @Override
    public void addLogInfo(LogInfoVo logInfoVo) {
        loginMapper.insertSelective(logInfoVo);
    }
}
