package com.zds.service;

import com.zds.entity.Customer;

import java.io.IOException;
import java.util.List;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/1 09:50
 */
public interface CustomerService {
    /**
     * 根据id查询客户信息
     *
     * @param id 客户id
     * @return Customer集合
     */
    List<Customer> selectOneCustomerById(Long id);

    /**
     * 插入客户信息
     *
     * @param customer 客户信息
     * @return 记录条数
     * @throws IOException ex
     */
    Integer insertCustomer(Customer customer) throws IOException;

    /**
     * 根据客户id修改客户信息
     *
     * @param customer 客户信息
     * @return 记录条数
     */
    Integer updateCustomerById(Customer customer);

    /**
     * 查询客户信息
     *
     * @return Customer集合
     */
    List<Customer> selectOneCustomer();
}
