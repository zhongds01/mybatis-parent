package com.zds.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/1 09:50
 */
@Configuration
@MapperScan(value = "com.zds.mapper")
public class MybatisPlusConfig {

}
