package org.example.practicecode.algorithm.demo.demo000;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * 动态规划
 *
 * 核心：穷举求最值
 *
 * 三要素：重叠子问题，最优子结构，状态转移方程（最难）
 *
 * 优化：因为重叠子问题，暴力穷举会有重复浪费，所以使用【备忘录】或【dp table】优化穷举
 *
 * 1、动态规划问题一定具备【最优子结构】，所以才能通过子问题的最值得到原问题的最值 2、因为核心是穷举求最值，所以列出正确的【状态转移方程】才能正确的穷举
 *
 * 分析【状态转移方程】： 明确【状态】-> 定义 dp 数组/函数的含义 -> 明确【选择】 -> 明确 base case（基本案例）
 *
 */
public class Demo001 {

    private int[] coins = new int[] {1, 2, 5};
    private int count = 0;

    @Test
    public void test() {
        // System.out.println(dp(11));
        System.out.println(coinChange(11));
    }

    private int dp(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 0) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        int subproblem = 0;
        for (int coin : this.coins) {
            subproblem = dp(n - coin);
            System.out.println("n=" + n + "; coin=" + coin + "; count=" + count++);
            if (subproblem == -1) {
                continue;
            }
            res = Integer.min(res, 1 + subproblem);
        }
        return res != Integer.MAX_VALUE ? res : -1;
    }

    private int coinChange(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        // return dpMemo(n, map);
        int[] dpTable = new int[n + 1];
        Arrays.fill(dpTable = new int[n + 1], n + 1);
        return dpTable(n, dpTable);
    }

    private int dpMemo(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if (n == 0) {
            return 0;
        }
        if (n < 0) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        int subproblem = 0;
        for (int coin : this.coins) {
            subproblem = dpMemo(n - coin, map);
            System.out.println("n=" + n + "; coin=" + coin + "; count=" + count++);
            if (subproblem == -1) {
                continue;
            }
            res = Integer.min(res, 1 + subproblem);
        }
        map.put(n, res != Integer.MAX_VALUE ? res : -1);
        return map.get(n);
    }

    private int dpTable(int n, int[] dpTable) {
        dpTable[0] = 0;
        for (int i = 0; i < dpTable.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dpTable[i] = Integer.min(dpTable[i], 1 + dpTable[i - coin]);
            }
        }
        System.out.println(Arrays.toString(dpTable));
        return (dpTable[n] == n + 1) ? -1 : dpTable[n];
    }

}
