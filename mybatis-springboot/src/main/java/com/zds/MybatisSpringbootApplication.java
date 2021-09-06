package com.zds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/1 09:50
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zds.mapper")
public class MybatisSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisSpringbootApplication.class, args);
    }

}
