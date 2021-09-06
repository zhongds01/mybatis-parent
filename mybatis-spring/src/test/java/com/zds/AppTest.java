package com.zds;

import com.zds.mapper.CustomerMapper;
import com.zds.entity.Customer;
import com.zds.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for CustomMapper.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class AppTest {
    @Autowired
    private CustomerService customerService;

    /**
     * testSelectCustomerById
     */
    @Test
    public void testSelectCustomerById() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        CustomerMapper bean = applicationContext.getBean(CustomerMapper.class);
        bean.selectOneCustomerById(1415300753928499202L).forEach(System.out::println);
    }

    @Test
    public void testTransactionalInsert() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        CustomerService customerServiceImpl = applicationContext.getBean("customServiceImpl", CustomerService.class);
        customerServiceImpl.insertCustom(Customer.builder().id(1415300753928499206L).customerName("zhongdongsheng").customerPassword("123456").customerSex("男").customerTel("13260906627").customerEmail("zhongds01@163.com").customerAddress("Nanjing").isDeleted(0).version(0).build());
    }

    @Test
    public void testUpdate() {
        customerService.updateCustomById(Customer.builder().id(1415300753928499206L).customerName("shengdongzhong").customerPassword("654321").customerSex("男").customerTel("13260906627").customerEmail("zhongds01@163.com").customerAddress("Nanjing").isDeleted(0).version(0).build());
    }
}
