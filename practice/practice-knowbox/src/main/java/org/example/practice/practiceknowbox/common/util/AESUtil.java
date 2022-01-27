package org.example.practice.practiceknowbox.common.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangshuai
 * @date 2021/7/19 10:38 上午
 */
@Slf4j
public class AESUtil {

    private static final String CIPHER_MODE_PADDING = "AES/CBC/PKCS5Padding";

    private static final String KEY = "yestem11yestem11yestem11yestem11";

    /**
     * 初始化向量字符串
     */
    private static final String ivStr = KEY.substring(0, 16);

    /**
     * Initialization Vector,初始化向量。<br/>
     * 每次加密或解密之后，使用初始化向量与明文或密文异或。<br>
     * 加密时明文首先与IV异或，然后将结果进行块加密，得到的就是密文，然后输出的密文会作为下一个块加密的IV。<br/>
     * 解密时，先将密文的第一个块进行块解密，然后将结果与IV异或，就能得到明文，同时本次解密的输入密文作为下一个块解密的IV。
     */
    private static final IvParameterSpec IVP = new IvParameterSpec(ivStr.getBytes());

    /**
     * 指定密钥,与加密算法。内部会将CIPHER_MODE_PADDING切割。
     */
    private static final SecretKeySpec SECRET_KEY_SPEC = new SecretKeySpec(KEY.getBytes(), "AES");

    public static String decode(String text) {
        try {
            byte[] decode = Base64.getDecoder().decode(text);
            Cipher instance = Cipher.getInstance(CIPHER_MODE_PADDING);
            // mode为加密，指定密钥和初始化向量
            instance.init(Cipher.DECRYPT_MODE, SECRET_KEY_SPEC, IVP);
            return new String(instance.doFinal(decode));
        } catch (Exception e) {
            log.error("AESUtil解密异常,text:" + text, e);
        }
        return null;
    }

    public static String encode(String text) {
        try {
            Cipher instance = Cipher.getInstance(CIPHER_MODE_PADDING);
            // mode为加密，指定密钥和初始化向量
            instance.init(Cipher.ENCRYPT_MODE, SECRET_KEY_SPEC, IVP);
            byte[] bytes = instance.doFinal(text.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            log.error("AESUtil加密异常,text:" + text, e);
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException,
        InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        String encode = AESUtil
            .encode("1626665222||LiuNanXing||ISWSOEAP||cbb1dc44ebc39d651756255c453603db||2225666261||223.71.41.141");
        System.out.println("encode:" + encode);

        String str =
            "jxSRRw0ZILnBYWY7BNbkR/T7/y73wjYtUxH/ZF5qXVltjzyOgvVkq6vDimxsi8P9GCtADwzaxJrDLbo3tuTDCGDwiw1BZK0hD1/sgpuYvAPjwmrcj9lNM/k8twPWgC+PpBcxjNjc3sTy9EAOHswMEbSm59rnL01nEbgb+l8BnaM";
        String decode = AESUtil.decode(str);
        System.out.println("decode:" + decode);
    }

}
