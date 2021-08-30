package com.zds.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.zds.mapper")
public class MybatisPlusConfig {

}
