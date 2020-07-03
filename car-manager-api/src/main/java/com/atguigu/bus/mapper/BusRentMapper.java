package com.atguigu.bus.mapper;

import com.atguigu.bus.bean.BusRent;
import com.atguigu.bus.bean.BusRentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusRentMapper {
    long countByExample(BusRentExample example);

    int deleteByExample(BusRentExample example);

    int deleteByPrimaryKey(String rentid);

    int insert(BusRent record);

    int insertSelective(BusRent record);

    List<BusRent> selectByExample(BusRentExample example);

    BusRent selectByPrimaryKey(String rentid);

    int updateByExampleSelective(@Param("record") BusRent record, @Param("example") BusRentExample example);

    int updateByExample(@Param("record") BusRent record, @Param("example") BusRentExample example);

    int updateByPrimaryKeySelective(BusRent record);

    int updateByPrimaryKey(BusRent record);
}