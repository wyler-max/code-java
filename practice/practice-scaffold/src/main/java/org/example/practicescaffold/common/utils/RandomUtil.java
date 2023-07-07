package org.example.practicescaffold.common.utils;

import java.util.Random;

/**
 * @author jibaole
 * @version 1.0
 * @desc RandomString
 * @date 2018/7/25 下午5:01
 */

public class RandomUtil {
    /**
     * 获取随机字符串 a-z,A-Z,0-9
     *
     * @param length
     *            长度
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            sb.append(str.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    /**
     * 获取随机字符串 a-z
     *
     * @param length
     *            长度
     * @return
     */
    public static String getLowerLetter(int length) {
        String str = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            sb.append(str.charAt(random.nextInt(26)));
        }
        return sb.toString();
    }

    /**
     * 获取随机字符串 0-9
     *
     * @param length
     *            长度
     * @return
     */
    public static String getNumber(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            sb.append(str.charAt(random.nextInt(10)));
        }
        return sb.toString();
    }

    /**
     * 获取随机字符串 0-9,a-z,0-9 有两遍0-9，增加数字概率
     *
     * @param length
     *            长度
     * @return
     */
    public static String getLowerLetterNumber(int length) {
        String str = "0123456789abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            sb.append(str.charAt(random.nextInt(46)));
        }
        return sb.toString();
    }

    /**
     * 获取随机密码，lLength位小写英文+nLength位数字
     *
     * @param lLength
     *            字母长度
     * @param nLength
     *            数字长度
     * @return
     */
    public static String getPasswordSimple(int lLength, int nLength) {
        return getLowerLetter(lLength) + getNumber(nLength);
    }

    /**
     * 获取随机密码，包含英文和数字
     *
     * @param length
     *            密码长度
     * @return
     */
    public static String getPasswordComplex(int length) {
        String password;
        while (true) {
            password = getLowerLetterNumber(6);
            // 必须包含字母和数字
            if (password.matches("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}")) {
                return password;
            }
        }
    }

}
