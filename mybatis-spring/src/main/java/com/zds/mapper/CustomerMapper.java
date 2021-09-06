package com.zds.mapper;

import com.zds.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/1 09:50
 */
public interface CustomerMapper {
    /**
     * 根据id查询客户信息
     *
     * @param id 客户id
     * @return Customer集合
     */
    List<Customer> selectOneCustomerById(@Param("id") Long id);

    /**
     * 插入客户
     *
     * @param customer 客户信息
     * @return 记录条数
     */
    Integer insertCustomer(@Param("custom") Customer customer);

    /**
     * 根据id更新客户信息
     *
     * @param customer 客户信息
     * @return 记录条数
     */
    Integer updateCustomerById(@Param("custom") Customer customer);

    /**
     * 查询所有客户信息
     *
     * @return Customer集合
     */
    List<Customer> selectCustomer();
}
