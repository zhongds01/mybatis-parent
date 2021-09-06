package com.zds.service;

import com.zds.base.IBaseService;
import com.zds.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/1 09:50
 */
public interface ICustomerService extends IBaseService<Customer> {
    /**
     * 自定义查询多Customer信息
     *
     * @return Customer集合
     */
    List<Customer> listCustomer();

    /**
     * 自定义根据id查询Customer信息
     *
     * @param id customerId
     * @return Customer
     */
    Customer selectOneCustomById(@Param("id") long id);
}
