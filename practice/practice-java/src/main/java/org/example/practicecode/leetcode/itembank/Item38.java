package org.example.practicecode.leetcode.itembank;

import org.junit.Test;

/**
 * 外观数列
 *
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 *
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 *
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 *
 * countAndSay(1) = "1"
 * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 第一项是数字 1
 * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
 * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
 * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
 * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
 *
 */
public class Item38 {


    @Test
    public void demo() {
        int target = 4;
        String ret = countAndSay(target);
        System.out.println(ret);
    }

    /**
     * countAndSay(1) = "1"
     * countAndSay(n) 是对 countAndSay(n-1)的描述
     * f(n) = {
     *     1, n=1;
     *     countAndSay(n-1), n > 1
     * }
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String s = countAndSay(n-1);
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int len = s.length();
        for (int i = 1; i < len+1; i++) {
            // 字符串最后一位直接拼接
            if (i == len) {
                sb.append(i - start).append(s.charAt(i-1));
            } else if (s.charAt(i) != s.charAt(start)) {
                sb.append(i-start).append(s.charAt(start));
                start = i;
            }
        }
        return sb.toString();
    }

    private String parse(String n) {
        char[] chars = n.toCharArray();
        StringBuilder sb = new StringBuilder();
        char tmp = chars[0];
        int tmpCount = 0;
        for (char aChar : chars) {
            if (aChar == tmp) {
                tmpCount++;
            } else {
                sb.append(tmpCount).append(tmp);
                tmpCount = 1;
                tmp = aChar;
            }
        }
        if (tmpCount > 0) {
            sb.append(tmpCount).append(tmp);
        }
        return sb.toString();
    }

}
