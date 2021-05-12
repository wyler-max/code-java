package org.example.practicecode.algorithm.demo.demo000;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 滑动窗口技巧
 *
 * 核心：双指针
 *  1、快慢指针：解决链表中的问题，比如链表中是否有环
 *  2、左右指针：解决数组或字符串中的问题，比如二分查找
 */
public class Demo004 {

    @Test
    public void demo1() {
        String src = "ADOBECODEBANC";
        String target = "ABC";
        String res = minString(src, target);
        System.out.println(res);
    }

    /**
     * 求包含目标字符串所有字母的最短字符串
     *
     * 例：ADOBECODEBANC 最小子串为 BANC
     */
    private String minString(String s, String t) {
        int left=0, right=0;
        String res = s;

        Map<String, Integer> winMap = Maps.newHashMap();
        Map<String, Integer> needMap = Maps.newHashMap();

        for (int i = 0; i < t.length(); i++) {
            String needChar = String.valueOf(t.charAt(i));
            needMap.put(needChar, needMap.getOrDefault(needChar, 0)+1);
        }

        int match = 0;

        while (right < s.length()) {
            // 右侧滑动
            String rightStr = String.valueOf(s.charAt(right));
            if (needMap.containsKey(rightStr)) {
                int rightWinVal = winMap.getOrDefault(rightStr, 0);
                rightWinVal++;
                winMap.put(rightStr, rightWinVal);
                if (rightWinVal == needMap.get(rightStr)) {
                    match++;
                }
            }
            right++;

            // 左侧滑动
            while (match == needMap.size()) {
                // 更新字符串
                String substring = s.substring(left, right);
                res = res.length() < substring.length() ? res: substring;

                String leftStr = String.valueOf(s.charAt(left));
                if (needMap.containsKey(leftStr)) {
                    int leftWinVal = winMap.getOrDefault(leftStr, 0);
                    if (leftWinVal > 0) {
                        leftWinVal--;
                        winMap.put(leftStr, leftWinVal);
                    }
                    if (leftWinVal < needMap.get(leftStr)) {
                        match--;
                    }
                }
                left++;
            }
        }
        return res;
    }

    @Test
    public void demo2() {
        String s = "cbaebabacdabc";
        String p = "abc";
        findAnagrams(s, p);
    }

    /**
     * 找到字符串中所有字母异位词
     *
     * 字母异位词：字母相同，排列顺序不同
     * 例：cbaebabacdabc 中 abc 的异位词的位置分别是 0，6
     */
    private void findAnagrams(String s, String p) {

        int left=0, right=0;
        Map<String, Integer> winMap = Maps.newHashMap();
        Map<String, Integer> targetMap = Maps.newHashMap();

        List<Integer> res = Lists.newArrayList();

        for (int i = 0; i < p.length(); i++) {
            String pStr = String.valueOf(p.charAt(i));
            targetMap.put(pStr, targetMap.getOrDefault(pStr, 0) + 1);
        }

        int match = 0;

        while (right < s.length()) {
            String rightStr = String.valueOf(s.charAt(right));
            if (targetMap.containsKey(rightStr)) {
                int rightWinVal = winMap.getOrDefault(rightStr, 0);
                rightWinVal++;
                winMap.put(rightStr, rightWinVal);
                if (rightWinVal == targetMap.get(rightStr)) {
                    match++;
                }
            }
            right++;
            while (match == targetMap.size()) {
                // 匹配字符串
                String substring = s.substring(left, right);
                if (p.length() == substring.length() && !p.equals(substring)) {
                    res.add(left);
                }

                String leftStr = String.valueOf(s.charAt(left));
                if (targetMap.containsKey(leftStr)) {
                    int leftWinVal = winMap.getOrDefault(leftStr, 0);
                    if (leftWinVal > 0) {
                        leftWinVal--;
                        winMap.put(leftStr, leftWinVal);
                    }
                    if (leftWinVal < targetMap.get(leftStr)) {
                        match--;
                    }
                }
                left++;
            }
        }
        System.out.println(res);
    }

    @Test
    public void demo3() {
        String s = "abcabcbb";
        subString(s);
    }

    /**
     * 无重复字符串的最长子串的长度
     *
     * 例：abcabcbb 最长子串的长度为 3
     */
    private void subString(String s) {
        int left = 0, right = 0;
        Map<String, Integer> map = Maps.newHashMap();

        int len = 0;

        while (right < s.length()) {
            String s1 = String.valueOf(s.charAt(right));
            int s1Val = map.getOrDefault(s1, 0);
            map.put(s1, s1Val+1);
            right++;

            while (map.getOrDefault(s1, 0) > 1) {
                String s2 = String.valueOf(s.charAt(left));
                int s2Val = map.getOrDefault(s2, 0);
                map.put(s2, s2Val-1);
                left++;
            }

            len = Math.max(len, (right-left));
        }
        System.out.println(len);
    }
}
