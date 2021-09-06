package com.zds.service;

import com.zds.entity.Customer;

import java.util.List;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/1 09:50
 */
public interface CustomerService {
    /**
     * 根据客户id查询客户信息
     *
     * @param id 客户id
     * @return Customer集合
     */
    List<Customer> selectOneCustomById(Long id);

    /**
     * 插入客户信息
     *
     * @param customer 客户信息
     * @return 记录条数
     */
    Integer insertCustom(Customer customer);

    /**
     * 更新客户信息
     *
     * @param customer 客户信息
     * @return 记录条数
     */
    Integer updateCustomById(Customer customer);

    /**
     * 查询一个客户信息
     *
     * @return Customer集合
     */
    List<Customer> selectOneCustom();
}
