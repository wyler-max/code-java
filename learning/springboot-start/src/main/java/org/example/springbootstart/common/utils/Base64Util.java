package org.example.springbootstart.common.utils;

import java.util.Base64;

/**
 * Base64 加解密工具类
 */
public class Base64Util {

    /**
     * 默认编码集
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    public static byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public static String encode(byte[] source) {
        return Base64.getEncoder().encodeToString(source);
    }

    /**
     * Base64 decode URL Safe
     *
     * @param base64
     * @return
     */
    public static byte[] decodeurlsafe(String base64) {
        return Base64.getUrlDecoder().decode(base64);
    }

    /**
     * Base64 encode URL Safe
     *
     * @param source
     * @return
     */
    public static String encodeurlSafe(byte[] source) {
        return Base64.getUrlEncoder().encodeToString(source);
    }
}
