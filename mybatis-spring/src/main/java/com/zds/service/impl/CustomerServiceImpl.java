package com.zds.service.impl;

import com.zds.mapper.CustomerMapper;
import com.zds.entity.Customer;
import com.zds.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/1 09:50
 */
@Service(value = "customServiceImpl")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<Customer> selectOneCustomById(Long id) {
        return customerMapper.selectOneCustomerById(id);
    }

    /**
     * 开启事务,验证该方法执行过程中报错是否会自动回滚
     * 如果不使用事务注解开启事务管理，该方法会成功插入数据
     * 如果使用注解开启事务，则会自动回滚之前的插入操作
     *
     * @param customer custom
     * @return int
     */
    @Transactional
    @Override
    public Integer insertCustom(Customer customer) {
        int rows = customerMapper.insertCustomer(customer);
        int i = 1 / 0;
        return rows;
    }

    @Override
    public Integer updateCustomById(Customer customer) {
        return customerMapper.updateCustomerById(customer);
    }

    @Override
    public List<Customer> selectOneCustom() {
        return customerMapper.selectCustomer();
    }
}
