package com.atguigu.sys.service;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.ResultObj;
import com.atguigu.sys.bean.SysLogLogin;
import com.atguigu.sys.vo.LogInfoVo;

import java.util.List;

public interface LogInfoService {


    void dellogInfo(LogInfoVo logInfoVo);

    DataGridView queryAllLogInfo(LogInfoVo logInfoVo);

    void deleteBatchLogInfo(LogInfoVo logInfoVo);

    void addLogInfo(LogInfoVo logInfoVo);
}
