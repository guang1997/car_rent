package com.atguigu.sys.service;

import com.atguigu.car.utils.DataGridView;
import com.atguigu.sys.bean.SysRole;
import com.atguigu.sys.vo.RoleVo;

import java.util.List;

public interface RoleService {
    List<SysRole> getAllRole(RoleVo roleVo);

    public List<SysRole> getAllRoleById(RoleVo roleVo, Integer id);

    DataGridView loadAllRole(RoleVo roleVo);

    void addRole(RoleVo roleVo);

    void delRole(RoleVo roleVo);

    void deleteBatchRole(RoleVo roleVo);

    void updateRole(RoleVo roleVo);

    DataGridView initRoleMenuTreeJson(Integer roleid);

    void saveRoleMenu(RoleVo roleVo);
}
