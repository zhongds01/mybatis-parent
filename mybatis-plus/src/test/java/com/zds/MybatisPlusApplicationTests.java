package com.zds;

import com.zds.mapper.CustomMapper;
import com.zds.entity.Custom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    private CustomMapper customMapper;

    @Test
    void testInsert() {
        Custom custom = Custom.builder().customName("zhouniang").customPwd("123456").customSex("男").customTel("13260900000").customEmail("zhongds01@163.com").customAddress("Wuxi").build();
        int rows = customMapper.insert(custom);
        System.out.println("insert rows = " + rows);
    }

    @Test
    void testSelectById() {
        Custom custom = customMapper.selectById(1415300753928499202L);
        System.out.println(custom);
    }


}
