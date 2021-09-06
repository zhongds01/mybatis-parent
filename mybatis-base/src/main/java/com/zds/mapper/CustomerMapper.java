package com.zds.mapper;

import com.zds.entity.Customer;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/6 22:27
 */
public interface CustomerMapper {
    /**
     * 根据id查询客户信息
     *
     * @param id id
     * @return Customer
     */
    Customer selectOneCustomerById(@Param("id") Long id);
}
