package org.example.practice.practicecode.javalang.utils.codec;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

import org.junit.Test;

/**
 * 在Java 8中，Base64编码已经成为Java类库的标准。 Java 8 内置了 Base64 编码的编码器和解码器。
 *
 * Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：基本/URL/MIME
 *
 * 内嵌类： static class Base64.Decoder static class Base64.Encoder
 */
public class Base64Test {

    @Test
    public void testBase64() {
        // 使用基本编码
        String originString = "java8?feature";
        String base64encodedString = null;
        try {
            System.out.println("原始字符串: " + originString);

            base64encodedString = Base64.getEncoder().encodeToString(originString.getBytes(StandardCharsets.UTF_8));
            System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);

            // 解码
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
            System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));

            base64encodedString = Base64.getUrlEncoder().encodeToString(originString.getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 10; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }

            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
