package com.zds;

import com.zds.entity.Custom;
import com.zds.service.CustomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisSpringbootApplicationTests {

    @Autowired
    private CustomService customService;

    @Test
    void contextLoads() {
        System.out.println(1);
    }

    @Test
    void testUpdateCustomById() {
        customService.updateCustomById(Custom.builder().id(1415300753928499206L).customName("shengdongzhong").customPwd("654321").customSex("男").customTel("13260906627").customEmail("zhongds01@163.com").customAddress("Nanjing").status("E").build());
    }

}
