package com.atguigu.sys.controller;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.ResultObj;
import com.atguigu.sys.bean.SysLogLogin;
import com.atguigu.sys.service.LogInfoService;
import com.atguigu.sys.vo.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/logInfo")
@RestController
public class LogInfoController {

    @Autowired
    LogInfoService logInfoService;


    /**
     * 用于删除日志信息
     * @param logInfoVo
     * @return
     */
    @RequestMapping(value = "/dellogInfo")
    public ResultObj dellogInfo(LogInfoVo logInfoVo) {
        try {
            logInfoService.dellogInfo(logInfoVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 用于批量删除日志信息
     * @param logInfoVo
     * @return
     */
    @RequestMapping(value = "/deleteBatchLogInfo")
    public ResultObj deleteBatchLogInfo(LogInfoVo logInfoVo) {
        try {
            logInfoService.deleteBatchLogInfo(logInfoVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 用于查找显示所有的日志信息
     * @param logInfoVo
     * @return
     */
    @RequestMapping(value = "/loadlogInfo")
    public DataGridView loadlogInfo(LogInfoVo logInfoVo) {

        return logInfoService.queryAllLogInfo(logInfoVo);
    }
}
