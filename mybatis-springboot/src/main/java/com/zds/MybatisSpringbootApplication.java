package com.zds;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/1 09:50
 */
@MapperScan(basePackages = "com.zds.mapper")
@EnableEncryptableProperties
@SpringBootApplication
public class MybatisSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisSpringbootApplication.class, args);
    }

}
