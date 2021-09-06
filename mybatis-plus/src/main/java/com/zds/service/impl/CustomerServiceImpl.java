package com.zds.service.impl;

import com.zds.base.BaseServiceImpl;
import com.zds.entity.Customer;
import com.zds.mapper.CustomerMapper;
import com.zds.service.ICustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhouliang
 * @since 2021-8-31下午 9:19
 */
@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl extends BaseServiceImpl<CustomerMapper, Customer> implements ICustomerService {
    @Autowired
    CustomerMapper customerMapper;


    @Override
    public List<Customer> listCustomer() {
        return customerMapper.listCustomer();
    }

    @Override
    public Customer selectOneCustomById(long id) {
        return customerMapper.selectOneCustomById(id);
    }
}
