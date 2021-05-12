package org.example.springbootstart.common.utils;

import org.example.springbootstart.common.model.UserToken;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.zip.CRC32;

/**
 * token处理类
 */
public class TokenUtil {
    private static final int SLAT = 20200416;

    /**
     * 生成TOKEN
     *
     * @param id 用户id
     * @return
     */
    public static String generateToken(long id) {
        return generateToken(new UserToken(id));
    }

    /**
     * 原始内容 CRC(4)+LOGIN_TIME(4)+USER_NAME
     *
     * @param token
     * @return
     */
    public static String generateToken(UserToken token) {
        CRC32 crc32 = new CRC32();
        crc32.update(SLAT);
        byte[] loginTimeBytes = Ints.toByteArray(token.getLoginTime());
        crc32.update(loginTimeBytes);
        byte[] idBytes = Longs.toByteArray(token.getId());
        crc32.update(idBytes);
        int checkSum = (int)crc32.getValue();

        byte[] data = Bytes.concat(Ints.toByteArray(checkSum), loginTimeBytes, idBytes);
        return new String(Base64Util.encodeurlSafe(data));
    }

    /**
     * 从token解析出数据
     *
     * @param token
     * @return
     */
    public static UserToken parseToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        try {
            byte[] encodeBytes = Base64Util.decodeurlsafe(token);
            if (encodeBytes.length != 16) {
                return null;
            }
            int checkSum = Ints.fromByteArray(Arrays.copyOfRange(encodeBytes, 0, 4));
            byte[] loginTimeBytes = Arrays.copyOfRange(encodeBytes, 4, 8);
            byte[] idBytes = Arrays.copyOfRange(encodeBytes, 8, 16);
            CRC32 crc32 = new CRC32();
            crc32.update(SLAT);
            crc32.update(loginTimeBytes);
            crc32.update(idBytes);

            if (checkSum == ((int)crc32.getValue())) {
                return new UserToken(Longs.fromByteArray(idBytes), Ints.fromByteArray(loginTimeBytes));
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
        System.out.println(JsonUtil.toJson(parseToken("token1")));
    }
}
