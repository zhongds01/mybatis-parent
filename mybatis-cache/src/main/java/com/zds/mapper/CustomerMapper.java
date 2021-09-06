package com.zds.mapper;

import com.zds.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/6 22:27
 */
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
}
