package com.zds.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * EncryptUtils
 *
 * @author zhongdongsheng
 * @since 2022/3/3
 */
public class EncryptUtils {
    private static final byte[] KEY = new byte[]{95, 117, 55, 121, -23, 89, 97, -90, 25, 102, 88, -102, 79, 58, 106,
            54};

    public static String encrypt(String content) {
        // 随机生成密钥
        AES aes = SecureUtil.aes(KEY);
        return aes.encryptHex(content);
    }

    public static String decrypt(String content) {
        AES aes = SecureUtil.aes(KEY);
        return aes.decryptStr(content, CharsetUtil.CHARSET_UTF_8);
    }
}
