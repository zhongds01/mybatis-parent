package com.zds;

import com.zds.mapper.CustomMapper;
import com.zds.pojo.Custom;
import com.zds.service.CustomService;
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
    private CustomService customService;
    /**
     * testSelectCustomById
     */
    @Test
    public void testSelectCustomById() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        CustomMapper bean = applicationContext.getBean(CustomMapper.class);
        bean.selectOneCustomById(1415300753928499202L).forEach(System.out::println);
    }

    @Test
    public void testTransactionalInsert() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        CustomService customServiceImpl = applicationContext.getBean("customServiceImpl", CustomService.class);
        customServiceImpl.insertCustom(Custom.builder().id(1415300753928499206L).customName("zhongdongsheng").customPwd("123456").customSex("男").customTel("13260906627").customEmail("zhongds01@163.com").customAddress("Nanjing").status("U").build());
    }

    @Test
    public void testUpdate(){
        customService.updateCustomById(Custom.builder().id(1415300753928499206L).customName("shengdongzhong").customPwd("654321").customSex("男").customTel("13260906627").customEmail("zhongds01@163.com").customAddress("Nanjing").status("E").build());
    }
}
