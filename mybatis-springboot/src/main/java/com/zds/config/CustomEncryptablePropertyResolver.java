package com.zds.config;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import com.zds.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CustomEncryptablePropertyResolver
 *
 * @author zhongdongsheng
 * @since 2022/3/8
 */
public class CustomEncryptablePropertyResolver implements EncryptablePropertyResolver {
    @Autowired
    private CustomEncryptablePropertyDetector propertyDetector;

    @Override
    public String resolvePropertyValue(String s) {
        if (s != null && propertyDetector.isEncrypted(s)) {
            return EncryptUtils.decrypt(propertyDetector.unwrapEncryptedValue(s));
        }

        return s;
    }
}
