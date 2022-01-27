package org.example.practice.practiceknowbox.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yijiu.chen
 * @date 2020/04/20
 */
@Slf4j
public class StringsUtil {

    private static Pattern PATTERN = Pattern.compile("[A-Z]([a-z\\d]+)?");

    /**
     * 必须是规则的
     *
     * @param str
     * @return
     */
    public static String camelToUnderline(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        Matcher matcher = PATTERN.matcher(str);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toLowerCase());
            sb.append(matcher.end() == str.length() ? "" : "_");
        }
        return sb.toString();
    }

    public static String safeUrlEncode(String param) {
        if (StringUtils.isBlank(param)) {
            return StringUtils.EMPTY;
        }
        try {
            return URLEncoder.encode(param, CharEncoding.UTF_8);
        } catch (Exception e) {
            log.error("safeUrlEncode", e);
        }
        return StringUtils.EMPTY;
    }

    public static String safeUrldecode(String param) {
        if (StringUtils.isBlank(param)) {
            return StringUtils.EMPTY;
        }
        try {
            return URLDecoder.decode(param, CharEncoding.UTF_8);
        } catch (Exception e) {
            log.error("safeUrlEncode", e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 超过指定长度后进行截取并用...
     *
     * @param text
     * @param length
     * @return
     */
    public static String textOverflow(String text, int length) {
        return text.length() > length ? text.substring(0, length - 1) + "..." : text;
    }
}
