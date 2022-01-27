package org.example.practice.practiceknowbox.common.util;

import java.util.Arrays;
import java.util.zip.CRC32;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.Strings;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;

import org.example.practice.practiceknowbox.common.algo.Code64;
import org.example.practice.practiceknowbox.common.model.UserToken;

/**
 * token处理类
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
public class TokenUtil {
    private static final int SLAT = 20200416;

    /**
     * TOKEN的长度:LENGTH(4)+CRC(4)+LOGIN_TIME(4)+USER_NAME
     */
    private static final int TOKEN_LENGTH = 20;

    /**
     * 生成TOKEN
     */
    public static String generateToken(String id) {
        return generateToken(new UserToken(id));
    }

    /**
     * 原始内容 LENGTH(4)+CRC(4)+LOGIN_TIME(4)+USER_NAME
     */
    public static String generateToken(UserToken token) {
        CRC32 crc32 = new CRC32();
        crc32.update(Ints.toByteArray(token.getLength()));
        crc32.update(SLAT);
        byte[] loginTimeBytes = Ints.toByteArray(token.getLoginTime());
        crc32.update(loginTimeBytes);
        byte[] idBytes = Strings.toByteArray(token.getId());
        crc32.update(idBytes);
        int checkSum = (int)crc32.getValue();

        byte[] data =
            Bytes.concat(Ints.toByteArray(token.getLength()), Ints.toByteArray(checkSum), loginTimeBytes, idBytes);
        return new String(Code64.encode(data));
    }

    /**
     * 从token解析出数据
     */
    public static UserToken parseToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        try {
            byte[] encodeBytes = Code64.decode(token.toCharArray());
            if (encodeBytes.length < TOKEN_LENGTH) {
                return null;
            }
            byte[] lengthBytes = Arrays.copyOfRange(encodeBytes, 0, 4);;
            int checkSum = Ints.fromByteArray(Arrays.copyOfRange(encodeBytes, 4, 8));
            byte[] loginTimeBytes = Arrays.copyOfRange(encodeBytes, 8, 12);
            byte[] idBytes = Arrays.copyOfRange(encodeBytes, 12, encodeBytes.length);
            CRC32 crc32 = new CRC32();
            crc32.update(lengthBytes);
            crc32.update(SLAT);
            crc32.update(loginTimeBytes);
            crc32.update(idBytes);
            String s = Strings.fromByteArray(idBytes);
            if (checkSum == ((int)crc32.getValue()) && s.length() == Ints.fromByteArray(lengthBytes)) {
                return new UserToken(s, Ints.fromByteArray(loginTimeBytes));
            }
        } catch (Exception e) {
            // do nothing
        }
        return null;
    }

    public static boolean verify(String token) {
        return parseToken(token) != null;
    }

    public static void main(String[] args) {
        String token = TokenUtil.generateToken("oMqARs1c5ycVsrINXYWmtux7HDRo");
        // 1186KNDfZlxtJw99GGGGHHDQ-mB
        System.out.println(JsonUtil.toJson(parseToken(token)));
    }
}
