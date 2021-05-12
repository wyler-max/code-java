package org.example.practicecode.leetcode;

import org.junit.Test;

/**
 * 实现strStr函数
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
 * 如果不存在，则返回  -1。
 *
 * 示例：
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 */
public class Item27 {


    @Test
    public void demo() {
        int[] nums = new int[]{3,2,2,3};
        int val = 3;
        String hayStack = "hello";
        String needle = "ll";
        System.out.println(strStr(hayStack, needle));
    }

    // hhe he O(mn)
    public int strStr(String hayStack, String needle) {
        if (hayStack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i < hayStack.length()-needle.length()+1; i++) {
            int j;
            for (j = 0; j < needle.length(); j++) {
                if (hayStack.charAt(i+j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }

    // KMP
    public int strStrKMP(String haystack, String needle) {
        int strLen = haystack.length(), subLen = needle.length();
        if (subLen == 0) {
            return 0;
        }
        if (strLen == 0) {
            return -1;
        }
        // 构建状态机 （next 数组）
        int[][] FSM = new int[subLen][256];
        int X = 0, match = 0;
        for (int i = 0; i < subLen; i++) {
            match = (int) needle.charAt(i);

            // 当前状态 + 匹配失败字符 = 孪生词缀状态 + 匹配字符
            for (int j = 0; j < 256; j++) {
                FSM[i][j] = FSM[X][j];
            }

            // 当前状态 + 匹配成功字符
            FSM[i][match] = i + 1;
            if (i > 1) {
                // 下一孪生前缀状态 = X + match
                X = FSM[X][match];
            }
        }
        // 匹配子串
        int state = 0;
        for (int i = 0; i < strLen; i++) {
            state = FSM[state][haystack.charAt(i)];
            if (state == subLen) {
                return i - subLen + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
    }
}
