package org.example.practice.practicecode.skill.leetcode.itembank;

import org.junit.Test;

/**
 * 最长公共前缀
 *
 */
public class Item14 {

    @Test
    public void demo() {
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix2(strs, 0, 2));
    }

    private String longestCommonPrefix(String[] strs) {
        if (strs.length <= 0) {
            return "";
        }
        String ret =  strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(ret) != 0) {
                ret = ret.substring(0, ret.length()-1);
                if (ret.isEmpty()) {
                    return "";
                }
            }
        }
        return ret;
    }

    /**
     * 分治法
     */
    private String longestCommonPrefix2(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (start+end)/2;
            String leftStr = longestCommonPrefix2(strs, start, mid);
            String rightStr = longestCommonPrefix2(strs, mid+1, end);
            return compareStr(leftStr, rightStr);
        }
    }

    private String compareStr(String leftStr, String rightStr) {
        int minLen = Math.min(leftStr.length(), rightStr.length());
        for (int i = 0; i < minLen; i++) {
            if (leftStr.charAt(i) != rightStr.charAt(i)) {
                return leftStr.substring(0, i);
            }
        }
        return leftStr.substring(0, minLen);
    }

}
