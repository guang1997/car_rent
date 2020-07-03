package com.atguigu.sys.service.impl;

import com.atguigu.sys.bean.SysRoleUserExample;
import com.atguigu.sys.mapper.SysRoleUserMapper;
import com.atguigu.sys.service.UserRoleService;
import com.atguigu.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    SysRoleUserMapper roleUserMapper;

    /**
     * 用于保存用户角色
     *
     * @param userVo
     */
    @Override
    public void saveUserRole(UserVo userVo) {
        // 根据用户id删除用户角色
        List<Integer> roleIds = userVo.getIds();
        SysRoleUserExample exa = new SysRoleUserExample();
        exa.createCriteria().andUidEqualTo(userVo.getUserid());
        roleUserMapper.deleteByExample(exa);
        if (roleIds != null && roleIds.size() > 0) {
            // 将新的用户角色存入到数据库中
            roleUserMapper.batchInsertUserRole(userVo.getUserid(), roleIds);
        }

    }
}
