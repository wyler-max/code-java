package org.example.practicescaffold.utils;

import java.util.Base64;

/**
 * @author yanyueming
 */
public class Base64Utils {

    /**
     * 默认编码集
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    @SuppressWarnings("WeakerAccess")
    public static byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    @SuppressWarnings("WeakerAccess")
    public static String encode(byte[] source) {
        return Base64.getEncoder().encodeToString(source);
    }

    public static byte[] decodeBase64(byte[] source) {
        return Base64.getDecoder().decode(source);
    }

    public static byte[] encodeBase64(byte[] source) {
        return Base64.getEncoder().encode(source);
    }

    /**
     * Base64 decode URLSafe
     *
     * @param base64
     * @return
     */
    @SuppressWarnings("WeakerAccess")
    public static byte[] decodeURLSafe(String base64) {
        return Base64.getUrlDecoder().decode(base64);
    }

    /**
     * Base64 encode URLSafe
     *
     * @param source
     * @return
     */
    @SuppressWarnings("WeakerAccess")
    public static String encodeURLSafe(byte[] source) {
        return Base64.getUrlEncoder().encodeToString(source);
    }
}
