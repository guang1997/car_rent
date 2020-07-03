package com.atguigu.bus.mapper;

import com.atguigu.bus.bean.BusChecks;
import com.atguigu.bus.bean.BusChecksExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusChecksMapper {
    long countByExample(BusChecksExample example);

    int deleteByExample(BusChecksExample example);

    int deleteByPrimaryKey(String checkid);

    int insert(BusChecks record);

    int insertSelective(BusChecks record);

    List<BusChecks> selectByExample(BusChecksExample example);

    BusChecks selectByPrimaryKey(String checkid);

    int updateByExampleSelective(@Param("record") BusChecks record, @Param("example") BusChecksExample example);

    int updateByExample(@Param("record") BusChecks record, @Param("example") BusChecksExample example);

    int updateByPrimaryKeySelective(BusChecks record);

    int updateByPrimaryKey(BusChecks record);
}