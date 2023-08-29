package org.example.practice.practicecode.skill.algorithm.demo.demo001;


import org.junit.Test;

import java.io.LineNumberReader;

/**
 * 动态规划技巧：状态机
 *
 * 打家劫舍系列问题： House Robber
 * 打家劫舍系列总共有三道，难度设计非常合理，层层递进。
 * 第一道是比较标准的动态规划问题。
 * 第二道融入了环形数组的条件。
 * 第三道更绝，把动态规划的自底向上和自顶向下解法和二叉树结合起来，我认为很有启发性。
 *
 * 作为一个专业盗贼，计划打劫所有的房屋。
 * 每个房间内都藏有一定的现金，影响你的唯一制约因素就是相邻的房屋装有相互联通的防盗系统。
 * 如果相邻的房屋在同一晚上被盗贼闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 注意：不能同时参与多笔交易（再次购买前必须抛掉所有的股票）
 * 示例1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号 price=1, 偷窃 3 号 price=3, count = 1+3 = 4
 *
 * 示例2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：count = 2+9+1 = 12
 *
 */
public class Demo008 {

    /**
     * 穷举框架：
     *
     * 动态规划的特征很明显，解决动态规划问题就是找「状态」和「选择」。
     *
     * 状态：（变量因素）
     * 1、房号 i，制约因素：a.i 取值范围，0<= i < n ；b.i 间隔i_next > i+1 ;
     * 2、start 开始抢的位置
     *
     * 选择：
     * 0 no_rob 不抢；1 rob 抢劫
     *
     * 状态转移方程：
     * rob(num[2..n]) = max(select no_rob, select rob)
     *                = max(rob([3..n]), num[2]+rob([4..n]))
     *
     */

    @Test
    public void demo1() {
        int[] nums = new int[] {1,2,3,1};
        System.out.println(rob1(nums, 0));
    }
    private int rob1(int[] nums, int start) {
        int n = nums.length;
        if (start >= n) {
            return 0;
        }
        return Math.max(
                // 不抢，然后去下家
                rob1(nums, start+1),
                // 抢，然后去下下家
                nums[start] + rob1(nums, start+2)
        );
    }

}
