package org.example.practicecode.leetcode.test;

import java.util.Arrays;

/**
 * @author wangyulin
 * @date 2021/4/20
 */
public class Test {

    int[] mem;

    @org.junit.Test
    public void test() {
        int[] coins = new int[] {1, 2, 5};
        int amount = 13;
        System.out.println("amount=" + amount);
        mem = new int[amount + 1];
        Arrays.fill(mem, -1);
        System.out.println(dp(coins, amount));
    }

    /**
     * @formatter:off
     * 定义：要凑出金额 n，至少要 f(n)=dp(coins, n) 个硬币
     * 1、状态转移方程框架：base case amount=0 select=0, 状态amount, 选择coins[i], dp(int[] coins,int amount)
     * 2、状态转移方程
     *  f(n) = {
     *      -1,n<0
     *      0,n=0
     *      min(f(n-coin)|coin∈coins{1,2,5}),n>0
     * }
     * 3、时间复杂度 O(n^k)*O(k)=O(k*n^k)
     * @formatter:on
     */
    int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (mem[amount] != -1) {
            return mem[amount];
        }
        int ret = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = coinChange(coins, amount - coin);
            if (sub == -1) {
                continue;
            }
            ret = Math.min(ret, sub + 1);
        }
        mem[amount] = ret == Integer.MAX_VALUE ? -1 : ret;
        return mem[amount];
    }

    /**
     * @formatter:off
     * dp[n] = {
     *      -1,n<0
     *      0,n=0
     *      min(dp[n-1]+1, dp[n-coin]+1)|coin∈coins{1,2,5}
     * }
     * @formatter:on
     */
    int dp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        if (amount < 0) {
            return -1;
        }
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int ret = dp[i - 1] + 1;
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                ret = Math.min(ret, dp[i - coin] + 1);
            }
            dp[i] = ret;
        }
        return dp[amount];
    }
}
