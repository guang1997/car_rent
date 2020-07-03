package com.atguigu.bus.mapper;

import com.atguigu.bus.bean.BusCustomer;
import com.atguigu.bus.bean.BusCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusCustomerMapper {
    long countByExample(BusCustomerExample example);

    int deleteByExample(BusCustomerExample example);

    int deleteByPrimaryKey(String identity);

    int insert(BusCustomer record);

    int insertSelective(BusCustomer record);

    List<BusCustomer> selectByExample(BusCustomerExample example);

    BusCustomer selectByPrimaryKey(String identity);

    int updateByExampleSelective(@Param("record") BusCustomer record, @Param("example") BusCustomerExample example);

    int updateByExample(@Param("record") BusCustomer record, @Param("example") BusCustomerExample example);

    int updateByPrimaryKeySelective(BusCustomer record);

    int updateByPrimaryKey(BusCustomer record);
}