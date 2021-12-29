package org.example.practicecode.leetcode.itembank;

import org.junit.Test;

/**
 * 最大子序和
 *
 *
 *
 */
public class Item53 {


    @Test
    public void demo() {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int ret = maxSubArray(nums);
        System.out.println(ret);
    }


    public int maxSubArray(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        }
        int len = nums.length;
        int sumMax = 0;
        for (int i = 0; i < len; i++) {
            int tmpMax = 0;
            int tmpSum = 0;
            for (int j = i; j < len; j++) {
                tmpSum += nums[j];
                tmpMax = Math.max(tmpMax, tmpSum);
            }
            sumMax = Math.max(sumMax, tmpMax);
        }
        return sumMax;
    }
}
