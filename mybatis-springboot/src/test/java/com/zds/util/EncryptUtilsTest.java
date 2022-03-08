package com.zds.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * todo:description
 *
 * @author zhongdongsheng
 * @since 2022/3/3
 */
@SpringBootTest
class EncryptUtilsTest {
    @Test
    void encrypt() {
        System.out.println(EncryptUtils.encrypt("123456"));
    }

    @Test
    void decrypt() {
        System.out.println(EncryptUtils.decrypt("71b6e2b5b0c5390c1f9ec2c918d7d686"));
    }
}