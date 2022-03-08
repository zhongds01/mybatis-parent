package com.zds;

import com.zds.entity.Customer;
import com.zds.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MybatisSpringbootApplicationTests {

    @Autowired
    private CustomerService customerService;

    @Test
    void contextLoads() {
        System.out.println(1);
    }

    @Test
    void testUpdateCustomerById() {
        customerService.updateCustomerById(Customer.builder().id(1415300753928499216L).customerName("zhongdongsheng").customerPassword("123456").customerSex("男").customerTel("13260906627").customerEmail("zhongds01@163.com").customerAddress("Nanjing").isDeleted(0).version(0).build());
    }

    @Test
    void testInsert() throws IOException {
        customerService.insertCustomer(Customer.builder().id(1415300753928499216L).customerName("shengdongzhong").customerPassword("654321").customerSex("男").customerTel("13260906627").customerEmail("zhongds01@163.com").customerAddress("Nanjing").isDeleted(0).version(0).build());
    }

}
