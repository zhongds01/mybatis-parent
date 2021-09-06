package com.zds.service.impl;

import com.zds.mapper.CustomerMapper;
import com.zds.entity.Customer;
import com.zds.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service(value = "customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<Customer> selectOneCustomerById(Long id) {
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
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertCustomer(Customer customer) throws IOException {
        int rows = customerMapper.insertCustomer(customer);
        try {
            throw new IOException("io exception");
        } catch (IOException e) {
            throw e;
        }
        // return rows;
    }

    @Override
    public Integer updateCustomerById(Customer customer) {
        return customerMapper.updateCustomerById(customer);
    }

    @Override
    public List<Customer> selectOneCustomer() {
        return customerMapper.selectCustomer();
    }
}
