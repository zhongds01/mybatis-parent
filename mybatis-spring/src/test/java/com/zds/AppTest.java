package com.zds;

import com.zds.mapper.CustomMapper;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for CustomMapper.
 */
public class AppTest {
    /**
     * testSelectCustomById
     */
    @Test
    public void testSelectCustomById() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        CustomMapper bean = applicationContext.getBean(CustomMapper.class);
        bean.selectOneCustomById(1415300753928499202L).forEach(System.out::println);
    }
}
