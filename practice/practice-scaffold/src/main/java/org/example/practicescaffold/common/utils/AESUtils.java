package org.example.practicescaffold.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.example.practicescaffold.utils.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * @author yanyueming
 */
@Slf4j
public class AESUtils {

    /**
     * 加密算法AES
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 默认编码集
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final String TRANSFORM = "AES/CBC/PKCS5Padding";

    private static final String AES_ECB_PKCS5PADDING = "AES/ECB/PKCS5Padding";

    private static final SecretKeySpec DEFAULT_KEY_SPEC =
        new SecretKeySpec("bkxt_20200302...".getBytes(), KEY_ALGORITHM);

    @SuppressWarnings("WeakerAccess")
    public static byte[] aes128CBCDecrypt(byte[] content, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(DEFAULT_CHARSET), KEY_ALGORITHM),
                new IvParameterSpec(iv.getBytes(DEFAULT_CHARSET)));
            return cipher.doFinal(content);
        } catch (Exception e) {
            log.error("aes128CBCDecrypt", e);
        }
        return null;
    }

    @SuppressWarnings("WeakerAccess")
    public static byte[] aes128CBCEncrypt(byte[] content, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(DEFAULT_CHARSET), KEY_ALGORITHM),
                new IvParameterSpec(iv.getBytes(DEFAULT_CHARSET)));
            return cipher.doFinal(content);
        } catch (Exception e) {
            log.error("aes128CBCEncrypt", e);
        }
        return null;
    }

    public static byte[] aes256CBCEncrypt(byte[] content, byte[] key, byte[] iv) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(TRANSFORM);
            SecretKeySpec keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
            byte[] cipherData = cipher.doFinal(content);
            return cipherData;
        } catch (Exception e) {
            log.error("aes256CBCEncrypt", e);
        }
        return null;
    }

    public static byte[] aes256CBCDecrypt(byte[] content, byte[] key, byte[] iv) {
        SecretKeySpec keySpec = null;
        try {
            keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
            byte[] decbbdt = cipher.doFinal(content);
            return decbbdt;
        } catch (Exception e) {
            log.error("aes256CBCDecrypt", e);
        }
        return null;
    }

    public static String aes256ECBEncrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5PADDING);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(DEFAULT_CHARSET), KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] result = cipher.doFinal(content.getBytes(DEFAULT_CHARSET));
            return Base64Utils.encode(result);
        } catch (Exception e) {
            log.error("aes256ECBEncrypt", e);
        }
        return null;
    }

    public static String aes256ECBDecrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5PADDING);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(DEFAULT_CHARSET), KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] result = cipher.doFinal(Base64Utils.decode(content));
            return new String(result);
        } catch (Exception e) {
            log.error("aes256ECBDecrypt", e);
        }
        return null;
    }

    public static String encrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, DEFAULT_KEY_SPEC);
            byte[] result = cipher.doFinal(content.getBytes(DEFAULT_CHARSET));
            return Base64Utils.encode(result);
        } catch (Exception e) {
            log.error("encrypt", e);
        }

        return null;
    }

    public static String decrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, DEFAULT_KEY_SPEC);
            byte[] result = cipher.doFinal(Base64Utils.decode(content));
            return new String(result);
        } catch (Exception e) {
            log.error("decrypt", e);
        }
        return null;
    }

    public static String encryptURLSafe(String content) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, DEFAULT_KEY_SPEC);
            byte[] result = cipher.doFinal(content.getBytes(DEFAULT_CHARSET));
            return Base64Utils.encodeURLSafe(result);
        } catch (Exception e) {
            log.error("encryptURLSafe", e);
        }
        return null;
    }

    public static String decryptURLSafe(String content) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, DEFAULT_KEY_SPEC);
            byte[] result = cipher.doFinal(Base64Utils.decodeURLSafe(content));
            return new String(result);
        } catch (Exception e) {
            log.error("decryptURLSafe", e);
        }
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String key = RandomUtil.getRandomString(32);

        String afterEnc = aes256ECBEncrypt("hello", key);
        System.out.println(afterEnc);
        String afterDec = aes256ECBDecrypt(afterEnc, key);
        System.out.println(afterDec);

        System.out.println(encrypt("hello"));
        System.out.println(decrypt(encrypt("hello")));

    }
}
