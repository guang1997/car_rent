package com.atguigu.sys.service;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.sys.bean.SysUser;
import com.atguigu.sys.vo.UserVo;

public interface SysUserService {

    SysUser doLogin(UserVo userVo);
    DataGridView loadAllUser(UserVo userVo);

    void addUser(UserVo userVo);

    void delRole(UserVo userVo);

    void updateUser(UserVo userVo);

    DataGridView initUserRoleTable(UserVo userVo);

    void changeUserPwd(Integer userid);
}
