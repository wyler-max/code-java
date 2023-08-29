package org.example.practice.practicecode.skill.algorithm.demo.demo001;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Temp {
    public static void main(String[] args) {
        int a = -123;
        System.out.println(a/10);
    }
    @Test
    public void demo() {
        int[] nums = new int[] {2, 7, 11, 15};
        int ret = reverse(1239);
        System.out.println(ret);
    }

    private int reverse(int x) {
        int ret = 0;
        while (x != 0) {
            if ((ret > 0 && ret*10 > (Integer.MAX_VALUE - x%10)) || (ret < 0 && ret*10 < (Integer.MIN_VALUE - x%10))) {
                return 0;
            }
            ret = ret*10 + x%10;
            x = x/10;
        }
        return ret;
    }
}
