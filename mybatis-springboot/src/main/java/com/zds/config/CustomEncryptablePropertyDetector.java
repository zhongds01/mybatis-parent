package com.zds.config;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;

/**
 * CustomEncryptablePropertyDetector
 *
 * @author zhongdongsheng
 * @since 2022/3/8
 */
public class CustomEncryptablePropertyDetector implements EncryptablePropertyDetector {
    public static final String PREFIX = "DS@";

    @Override
    public boolean isEncrypted(String s) {
        if (s != null && s.startsWith(PREFIX)) {
            return true;
        }

        return false;
    }

    @Override
    public String unwrapEncryptedValue(String s) {
        return s.substring(PREFIX.length());
    }
}
