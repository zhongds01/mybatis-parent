package com.zds;

import com.zds.entity.Customer;
import com.zds.mapper.CustomMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    @Qualifier(value = "customMapper")
    private CustomMapper customMapper;

    @Test
    void testInsert() {
        Customer customer = Customer.builder().customerName("zhouniang").customerPassword("123456").customerSex("男").customerTel("13260900000").customerEmail("zhongds01@163.com").customerAddress("Wuxi").build();
        int rows = customMapper.insert(customer);
        System.out.println("insert rows = " + rows);
    }

    @Test
    void testSelectById() {
        Customer customer = customMapper.selectById(1415300753928499202L);
        System.out.println(customer);
    }


}
