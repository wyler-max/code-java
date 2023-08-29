package org.example.practice.practicecode.skill.leetcode.itembank;

import org.junit.Test;

/**
 * 最长上升子列：
 *      给定一个无序的整数数组，找到其中最长上升子序列的长度
 * 举例
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 * 解题思路：动态规划
 */
public class Item300 {

    @Test
    public void demo() {
        //int[] nums = new int[]{10,9,2,5,3,7,101,18};
        int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
        System.out.println(lengthOfLIS(nums));
    }

    /**
     * dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度
     * [10,9,2,5,3,7,101,18]
     *  1  1 1 2 2 3 4   4
     *
     *  状态转移方程：
     *  dp[i] = max(dp[j])+1,其中 0<=j<=i 且 num[j] < num[i]
     *
     *  时间复杂度：O(n^2)
     */
    private int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int[] dp = new int[length];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
