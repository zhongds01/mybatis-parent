package com.zds.mapper;

import com.zds.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/1 09:50
 */
//@Mapper
public interface CustomerMapper {
    /**
     * 根据客户id查询客户信息
     *
     * @param id 客户id
     * @return Customer集合
     */
    List<Customer> selectOneCustomerById(@Param("id") Long id);

    /**
     * 插入客户信息
     *
     * @param customer 客户信息
     * @return 记录条数
     */
    Integer insertCustomer(@Param("customer") Customer customer);

    /**
     * 根据客户id更新客户信息
     *
     * @param customer 客户信息
     * @return 记录条数
     */
    Integer updateCustomerById(@Param("customer") Customer customer);

    /**
     * 查询客户信息
     *
     * @return Customer集合
     */
    List<Customer> selectCustomer();
}
