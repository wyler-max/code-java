package org.example.practicecode.algorithm.demo.demo001;



import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划技巧：状态机
 *
 * 股票问题：
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格，最多可以完成 k 笔交易。
 * 设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：不能同时参与多笔交易（再次购买前必须抛掉所有的股票）
 * 示例1：
 * 输入：[2,4,1], k = 2
 * 输出：2
 * 解释：第1天价格 2 买入，第2天价格 4 卖出，利润=2
 *
 * 示例2：
 * 输入：[3,2,6,5,0,3], k = 2
 * 输出：7
 * 解释：第2天价格 2 买入，第3天价格 6 卖出，利润=4; 第5天价格 0 买入，第6天价格 3 卖出，利润=4；总利润=4+3=7
 *
 */
public class Demo007 {

    /**
     * 穷举框架：
     *
     * 而这里，我们不用递归思想进行穷举，而是利用「状态」进行穷举。
     * 我们具体到每一天，看看总共有几种可能的「状态」，再找出每个「状态」对应的「选择」。
     * 我们要穷举所有「状态」，穷举的目的是根据对应的「选择」更新状态。
     * 听起来抽象，你只要记住「状态」和「选择」两个词就行，下面实操一下就很容易明白了。
     *
     * for 状态1 in 状态1的所有取值：
     *     for 状态2 in 状态2的所有取值：
     *         for ...
     *             dp[状态1][状态2][...] = 择优(选择1，选择2...)
     *
     * ① 选择：买入、卖出、无操作（buy, sell, rest）
     * 选择限制：
     * 1、sell 必须在 buy 之后。
     * 2、buy 必须在sell 之后
     * 3、rest 分两种情况：
     *  3.1 buy 后 rest ，持有 1
     *  3.2 sell 后 rest，未持有 0
     * 4、buy 时 k > 0
     *
     * ② 状态：3种，总共 n*k*2个状态
     * 1、天数 i
     * 2、允许交易的最大次数 k
     * 3、当前持有状态 1 或 0
     *
     * prices[0 .. n-1]
     * dp[i][k][0 or 1]
     * 0 <= i <= n-1：天数
     * 1 <= k <= K: 交易次数
     * 总状态：n * K * 2，全部穷举即可
     * 填充框架
     * for 0 <= i < n:
     *      for 1 <= k <= K:
     *          for s in (0, 1):
     *              dp[i][k][s] = max(buy, sell, rest);
     *
     * ③ 状态转移过程：
     *                    buy
     *                 ↗       ↘
     *    ↪ rest ↩  0            1 ↪ rest ↩
     *                 ↖       ↙
     *                    sell
     *
     * 状态转移方程：
     * dp[i][k][s] = {
     *     dp[i][k][0]
     *     dp[i][k][1]
     * }
     *
     * dp[i][k][0] = max(select rest, select sell)
     *             = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *
     * dp[i][k][1] = max(select rest, select buy)
     *             = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * ④ base case:
     *
     * dp[-1][k][0] = 0
     * 解释：0 <= i <= n，i=-1时，还未开始，利润为0
     *
     * dp[-1][k][1] = -inf
     * 解释：0 <= i <= n，i=-1时，还未开始，不可能持有股票，-inf 表示不可能的情况
     *
     * dp[i][0][0] = 0
     * 解释：1 <= k <= K，k=0时，不可交易，无法交易情况下，利润为0
     *
     * dp[i][0][1] = -inf
     * 解释：1 <= k <= K，k=0时，不可交易，无法交易的情况下，不可能持有股票，-inf 表示不可能的情况
     *
     * 总结：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = p[i][0][1] = -inf
     */

    /**
     * 第一题：k = 1
     *
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     *             = max(0, dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
     *             = max(dp[i-1][1][1], -prices[i])
     * 观察可知：k=1 总是成立，那么简化 k
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], -prices[i])
     */
    private int case10(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[n-1][0];
    }
    // 对 base case 做边界处理
    private int case11(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i -1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[n-1][0];
    }

    /**
     * 优化代码
     * 新状态仅和相邻状态有关，所以使用一个变量存储相邻变量，代替整个 dp 数组，空间复杂度降到 O(1)
     */
    private int case12(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    /**
     * 第二题：k = +inf
     * 如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的。可以这样改写框架：
     *
     * dp[i][k][0] = max(select rest, select sell)
     *             = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(select rest, select buy)
     *             = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *             = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
     *
     * 即 k 不在变化，那么忽略这个变量因素
     * 状态转移方程如下：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     *
     * 参考 case12 可写出代码
     */
    private int case22(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }

    /**
     * 第三题：k = +inf with cooldown
     * 每次 sell 之后要等一天才能继续交易。
     *
     * 状态转移方程如下：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
     *
     */
    private int case32(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; // 存储 dp[i-2][0]
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 + prices[i]);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }

    /**
     * 第四题：k = +inf with fee
     * 每次交易要支付手续费，只要把手续费从利润中减去即可
     *
     * 状态转移方程如下：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
     */
    private int case42(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int fee = 10;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
        }
        return dp_i_0;
    }

    /**
     * 第五题：k = 2
     *
     * k = 2 和前面题目的情况稍微不同，因为上面的情况都和 k 的关系不太大。
     * 要么 k 是正无穷，状态转移和 k 没关系了；要么 k = 1，跟 k = 0 这个 base case 挨得近，最后也没有存在感。
     *
     * 这道题 k = 2 和后面要讲的 k 是任意正整数的情况中，对 k 的处理就凸显出来了。
     *
     * 动态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * base case 总结：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = p[i][0][1] = -inf
     *
     * 之前因为 k 不影响结果，所以代码中消除掉了，现在穷举时却不能忽略 k 的影响
     *
     * 先列出 k=1,k=2的 base case
     * dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
     * dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], -prices[i])
     */
    private int case52(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }

    /**
     * 第六题：k = any integer
     *
     * 只需要考虑 k 过大时，导致 dp 数组过大，容易超内存，一般 k <= n/2，超过之后相当于 k = +inf
     *
     * 动态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * base case 总结：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = p[i][0][1] = -inf
     */
    private int case62(int[] prices, int maxK) {
        int n = prices.length;
        if (maxK > n / 2) {
            return case22(prices);
        }
        int[][][] dp = new int[n][maxK+1][2];
        for (int i = 0; i < n; i++) {
            for (int k = maxK; k >= 1; k--) {
                // 处理 base case
                if (i == 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = Integer.MIN_VALUE;
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }
        return dp[n-1][maxK][0];
    }

}
