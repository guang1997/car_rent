package com.atguigu.bus.service;

import com.atguigu.bus.bean.BusCustomer;
import com.atguigu.bus.vo.CustomerVo;
import com.atguigu.car.utils.DataGridView;

public interface CustomerService {
    DataGridView queryAllCustomer(CustomerVo customerVo);

    void addCustomer(CustomerVo customerVo);

    void updateCustomer(CustomerVo customerVo);

    void deleteBatchCustomer(CustomerVo customerVo);

    void delCustomer(CustomerVo customerVo);

    BusCustomer queryCustomerById(String identity);
}
