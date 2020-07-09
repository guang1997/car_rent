package com.atguigu.bus.controller;

import com.atguigu.bus.bean.BusCustomer;
import com.atguigu.bus.service.CustomerService;
import com.atguigu.bus.vo.CustomerVo;
import com.atguigu.car.utils.AppFileUtils;
import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.ExportListExcelUtils;
import com.atguigu.car.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RequestMapping(value = "/customer")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;


    /**
     * 用于获得所有的用户数据
     * @return
     */
    @GetMapping(value = "/loadAllCustomer")
    public DataGridView loadAllCustomer(CustomerVo customerVo) {

        return customerService.queryAllCustomer(customerVo);
    }

    /**
     * 用于添加客户
     * @return
     */
    @PostMapping(value = "/addCustomer")
    public ResultObj addcustomer(CustomerVo customerVo) {
        try {
            customerService.addCustomer(customerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 用于更新客户
     * @param customerVo
     * @return
     */
    @PostMapping(value = "/updateCustomer")
    public ResultObj updateCustomer(CustomerVo customerVo) {
        try {
            customerService.updateCustomer(customerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultObj.UPDATE_ERROR;
        }

    }

    /**
     * 用于批量删除客户
     * @param customerVo
     * @return
     */
    @PostMapping(value = "/deleteBatchCustomer")
    public ResultObj deleteBatchCustomer(CustomerVo customerVo) {
        try {
            customerService.deleteBatchCustomer(customerVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }

    }

    /**
     * 用于删除单个客户
     * @param customerVo
     * @return
     */
    @PostMapping(value = "/delCustomer")
    public ResultObj delCustomer(CustomerVo customerVo) {
        try {
            customerService.delCustomer(customerVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
