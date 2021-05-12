package org.example.practicecode.leetcode;

import org.junit.Test;

/**
 * 回文数
 *
 */
public class Item13 {

    @Test
    public void demo() {

    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int ret = 0;
        while (x != 0) {
            ret = ret*10 + x%10;
            x = x/10;
        }
        return ret == x;
    }

    public boolean isPalindrome2(int x) {
        String s = String.valueOf(x);
        int n = s.length();
        for (int i = 0; i < n/2; i++) {
            if (s.charAt(i) != s.charAt(n-1-i)) {
                return false;
            }
        }
        return true;
    }

}
