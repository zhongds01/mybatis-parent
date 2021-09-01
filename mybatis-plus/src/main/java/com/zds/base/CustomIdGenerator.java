package com.zds.base;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义主键生产策略实现类
 * 方式1：在类上使用@Component注解
 *
 * @author zhongdongsheng
 * @datetime 2021/9/1 16:27
 */
// @Component
@Slf4j
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public String nextUUID(Object entity) {
        log.info("使用自定义主键生成策略ASSIGN_UUID");
        return "111";
    }

    @Override
    public Number nextId(Object entity) {
        log.info("使用自定义主键生成策略ASSIGN_ID");
        return 123L;
    }
}
