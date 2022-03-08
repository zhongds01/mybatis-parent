package com.zds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JasyptConfig
 *
 * @author zhongdongsheng
 * @since 2022/3/3
 */
@Configuration
public class JasyptConfig {
    @Bean(name = "encryptablePropertyResolver")
    public CustomEncryptablePropertyResolver propertyResolver() {
        return new CustomEncryptablePropertyResolver();
    }

    @Bean(name = "encryptablePropertyDetector")
    public CustomEncryptablePropertyDetector propertyDetector() {
        return new CustomEncryptablePropertyDetector();
    }

}
