package org.example.practice.practicecode.skill.algorithm.demo.demo001;

import org.junit.Test;

import java.util.Arrays;

/**
 * 动态规划之子序列问题阶梯模板：
 *
 * 时间复杂度一般为：O(n^2)
 *
 * 两套解题模板：
 * 1、一维 dp 数组
 *
 * 2、二维 dp 数组
 */
public class Demo003 {

    /**
     * 模板一：一维 dp 数组
     *
     * 举例：最长递增子序列
     */
    private void template1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                //dp[i] = max|min(dp[i], dp[j]) + ...;
            }
        }
    }

    /**
     * 模板二：二维 dp 数组
     *
     * 举例：最长公共子序列
     *
     * dp 数组含义分为两种情况：
     * 1、涉及一个字符串
     *      在子数组 arr[i..j] 中，我们定义子序列的长度为 dp[i][j]
     *      举例：最长回文子序列
     * 2、涉及两个字符串
     *      在子数组 arr1[i..j]，arr2[i..j] 中，我们定义子序列 xxx 长度为 dp[i][j]
     *      举例：编辑距离、公共子序列
     */
    private void template2(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i] == nums[j]) {
                    //dp[i][j] = dp[i][j] + ...;
                } else {
                    //dp[i][j] = max|min();
                }
            }
        }
    }

    @Test
    public void demo() {
        String str = "apple";
        int ret = longestPalindromeSubseq(str);
        System.out.println(ret);
    }

    /**
     * 状态：s[i..j] i,j
     * 选择：i+1, j-1; 根据 dp[i+1][j-1]、dp[i+1][j]、dp[i][j-1]推导出 dp[i][j]
     * 穷举：
     * if s[i] == s[j]
     *  dp[i][j] = dp[i+1][j-1] +2
     * else
     *  dp[i][j] = max(dp[i+1][j], dp[i][j-1])
     *
     *      a   p   p   l   e   x
     * 0    1   2   3   4   5   6
     * 1    1
     * 2        1
     * 3            1
     * 4                1
     * 5                    1
     * 6                        1
     *
     * 两种遍历方式：
     * 1、斜着 i++ ,j++ ；
     * 2、逆序 i-- ,j++ i+1<=j<n
     *
     */
    private int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        //System.out.println(Arrays.deepToString(dp));
        int maxLen = Integer.MIN_VALUE;
        for (int i = n-1; i >= 0; i--) {
            for (int j = i ; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] +2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen;
    }

    /**
     * 数组矩阵：
     * 0,0 0,1 0,2 0,3 0,4
     * 1,0 1,1 1,2 1,3 1,4
     * 2,0 2,1 2,2 2,3 2,4
     * 3,0 3,1 3,2 3,3 3,4
     * 4,0 4,1 4,2 4,3 4,4
     *
     * 斜着遍历
     * 0,0 0,1 0,2 0,3 0,4
     *     1,1 1,2 1,3 1,4
     *         2,2 2,3 2,4
     *             3,3 3,4
     *                 4,4
     * 斜着遍历输出：
     * 0,0 1,1 2,2 3,3 4,4
     * 0,1 1,2 2,3 3,4
     * 0,2 1,3 2,4
     * 0,3 1,4
     * 0,4
     * 斜着遍历方程式：
     * i=0, i<n, i++
     *  j=0, j<n-i, j++
     *    d[j][i+j]
     *
     */
    @Test
    public void tmp() {
        int m = 7;
        int n = 5;
        String[][] arr = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = "("+i+","+j+")";
            }
        }

        // 计数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print(arr[j][j+i] + "\t");
            }
            System.out.println();
        }
    }
}
