package com.atguigu.sys.mapper;

import com.atguigu.sys.bean.SysRoleUserExample;
import com.atguigu.sys.bean.SysRoleUserKey;
import java.util.List;

import com.atguigu.sys.vo.UserVo;
import org.apache.ibatis.annotations.Param;

public interface SysRoleUserMapper {
    long countByExample(SysRoleUserExample example);

    int deleteByExample(SysRoleUserExample example);

    int deleteByPrimaryKey(SysRoleUserKey key);

    int insert(SysRoleUserKey record);

    int insertSelective(SysRoleUserKey record);

    List<SysRoleUserKey> selectByExample(SysRoleUserExample example);

    int updateByExampleSelective(@Param("record") SysRoleUserKey record, @Param("example") SysRoleUserExample example);

    int updateByExample(@Param("record") SysRoleUserKey record, @Param("example") SysRoleUserExample example);

    void batchInsertUserRole(@Param("userid") Integer userid, @Param("roleIds") List<Integer> roleIds);
}