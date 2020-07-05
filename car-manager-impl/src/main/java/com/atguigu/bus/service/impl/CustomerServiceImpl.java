package com.atguigu.bus.service.impl;

import com.atguigu.bus.bean.BusCustomer;
import com.atguigu.bus.bean.BusCustomerExample;
import com.atguigu.bus.mapper.BusCustomerMapper;
import com.atguigu.bus.service.CustomerService;
import com.atguigu.bus.vo.CustomerVo;
import com.atguigu.car.utils.DataGridView;
import com.atguigu.car.utils.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    BusCustomerMapper customerMapper;

    /**
     * 查询所有的客户数据
     * @param customerVo
     * @return
     */
    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        Page<Object> pages = PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
        BusCustomerExample exa = new BusCustomerExample();
        BusCustomerExample.Criteria criteria = exa.createCriteria();
        // 对模糊查询进行条件判断
        if (!StringUtil.isEmpty(customerVo.getCustname())) {
            criteria.andCustnameLike("%" + customerVo.getCustname() + "%");
        }
        if (!StringUtil.isEmpty(customerVo.getIdentity())) {
            criteria.andIdentityLike("%" + customerVo.getIdentity() + "%");
        }
        if (!StringUtil.isEmpty(customerVo.getAddress())) {
            criteria.andAddressLike("%" + customerVo.getAddress() + "%");
        }
        if (!StringUtil.isEmpty(customerVo.getPhone())) {
            criteria.andPhoneLike("%" + customerVo.getPhone() + "%");
        }
        if (!StringUtil.isEmpty(customerVo.getCareer())) {
            criteria.andCareerLike("%" + customerVo.getCareer() + "%");
        }
        if (customerVo.getStartTime() != null) {
            criteria.andCreatetimeGreaterThan(customerVo.getStartTime());
        }
        if (customerVo.getEndTime() != null) {
            criteria.andCreatetimeLessThan(customerVo.getEndTime());
        }
        // 性别
        if (customerVo.getSex() != null) {
            if (customerVo.getSex() == 1) {
                criteria.andSexEqualTo(1);
            } else {
                criteria.andSexEqualTo(0);
            }
        }

        List<BusCustomer> customers = customerMapper.selectByExample(exa);
        return new DataGridView(pages.getTotal(), customers);
    }

    /**
     * 用于添加客户
     * @param customerVo
     */
    @Override
    public void addCustomer(CustomerVo customerVo) {
        customerVo.setCreatetime(new Date());
        customerMapper.insertSelective(customerVo);
    }

    /**
     * 用于更新客户
     * @param customerVo
     */
    @Override
    public void updateCustomer(CustomerVo customerVo) {
        customerMapper.updateByPrimaryKeySelective(customerVo);
    }

    /**
     * 用于批量删除客户
     * @param customerVo
     */
    @Override
    public void deleteBatchCustomer(CustomerVo customerVo) {
        BusCustomerExample exa = new BusCustomerExample();
        exa.createCriteria().andIdentityIn(customerVo.getIds());
        customerMapper.deleteByExample(exa);
    }

    /**
     * 用于删除单个客户
     * @param customerVo
     */
    @Override
    public void delCustomer(CustomerVo customerVo) {
        customerMapper.deleteByPrimaryKey(customerVo.getIdentity());
    }

    /**
     * 根据客户身份证查询客户
     * @param identity
     * @return
     */
    @Override
    public BusCustomer queryCustomerById(String identity) {
        System.out.println("identity" + identity);
        return customerMapper.selectByPrimaryKey(identity);
    }

}
