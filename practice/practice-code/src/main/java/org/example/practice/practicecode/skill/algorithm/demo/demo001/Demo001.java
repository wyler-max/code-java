package org.example.practice.practicecode.skill.algorithm.demo.demo001;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * 动态规划系列 （又名数学归纳法）
 *
 * 核心：穷举求最值
 *
 * 三要素：
 *  1.重叠子问题 （一个递归解决方式中，包含非常多的子问题，而这些子问题绝大多数是重复的）
 *  2.最优子结构 （问题的最优解包含子问题的最优解。通过子问题最优解可推导出问题最优解；后面状态可以通过前面的状态推导出来）
 *  3.状态转移方程（最难）（穷举所有状态）
 *
 * 穷举优化：因为重叠子问题，暴力穷举会有重复浪费，所以使用【备忘录】或【dp table】优化
 *
 * 解释：
 * 1、动态规划问题一定具备【最优子结构】，所以才能通过子问题的最值得到原问题的最值
 *    (当最优子结构失效时，改造问题)
 * 2、因为核心是穷举求最值，所以列出正确的【状态转移方程】才能正确的穷举
 *
 * 确定最优子结构：
 *      子问题最优 -> 推导出 -> 整体最优；子问题之间必须相互独立！
 * 最优子结构举例：
 *      1. 每门功课最高 -> 功课最高；符合最优子结构
 *      2. 数学和语文成绩相互制约，此消彼长。则子问题之间并不独立，破坏了最优子结构。
 *
 * 分析【状态转移方程】思路：
 *      明确【状态】-> 定义 dp 数组/函数的含义 -> 明确【选择】 -> 明确 base case（基本案例）
 *
 * 案例：通过 1、斐波那契数列问题 和 2、凑零钱问题来详解动态规划的基本原理。
 *      前者主要是介绍什么是重叠子问题（斐波那契数列不涉及求最值，因此严格来说不是动态规划问题），
 *      后者主要关注如何列出状态转移方程。
 *
 */
public class Demo001 {

    /**
     * 斐波那契数列
     *
     * 举例：1 1 2 3 5 8 13 ... n = (n-1) + (n-2)
     *
     * 目标：介绍什么是重叠子问题
     *
     * 状态转移方程：
     *  f(n) = 1, n= 1, 2
     *         (n-1) + (n-2), n > 2
     */
    @Test
    public void demo1() {
        int n = 10;
        System.out.print(fib3(n));
    }

    /**
     * 暴力递归
     *
     * 通过递归树可以看到计算过程中，遇到了很多重复的子问题。
     * 例如：
     * f(20) = f(19) + f(18);
     *      f(19) = f(18) + f(17);
     *          ...
     *      f(18) = f(17) + f(16);
     *          ...
     * ...
     * f(5) = f(4) + f(3);
     *      f(4) = f(3) + f(2);
     *          f(3) = f(2) + f(1);
     *              f(2) = 1;
     *              f(1) = 1;
     *          f(2) = 1;
     *      f(3) = f(2) + f(1);
     *          f(2) = 1;
     *          f(1) = 1;
     * f(4) = f(3) + f(2);
     *      f(3) = f(2) + f(1);
     *          f(2) = 1;
     *          f(1) = 1;
     *      f(2) = 1;
     * f(3) = f(2) + f(1);
     * f(2) = 1;
     * f(1) = 1;
     * 从上面可以看到，子问题数量呈指数增长（2^(n-2)），且出现了非常多的重复计算
     *
     * 算法的时间复杂度：O(2^n)
     */
    private int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        System.out.println(n + " - ");
        return fib(n-1) + fib(n-2);
    }

    Map<Integer, Integer> map = Maps.newHashMap();
    /**
     * 使用备忘录 map，解决重叠子问题
     *
     * 算法的时间复杂度：O(n)
     */
    private int fib2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (map.get(n) != null) {
            return map.get(n);
        } else {
            System.out.println(n + " - ");
            int value = fib2(n-1) + fib2(n-2);
            map.put(n, value);
            return value;
        }
    }

    /**
     * 使用 dp table (dynamic programming) 数组，解决重叠子问题
     *
     * 算法的时间复杂度：O(n)
     */
    private int fib3(int n) {
        map.put(1, 1);
        map.put(2, 1);
        for (int i = 3; i <= n; i++) {
            map.put(i, map.get(i-1) + map.get(i-2));
        }
        return map.get(n);
    }

    /**
     * 凑零钱问题
     * 题目：给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，
     * 问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1
     *
     * 举例：1块，2块，5块三种硬币。总金额 amount=11，最少需要 3 枚硬币，即：11=5+5+1
     *
     * 目标：状态转移方程 和 最优子结构
     */
    @Test
    public void demo2() {
        int amount = 11;
        System.out.println(dp3(amount));
    }

    int[] coins = new int[]{5,2,1};
    /**
     *
     * 确定最优子结构：
     *  1. 硬币（子问题）之间相互独立
     *  2. 求最值，金额=amount时，硬币数量最少
     *
     * 状态转移方程：
     * 1. 明确状态 amount
     * 2. 明确 dp 函数的定义，当前目标金额是 n， 至少需要 dp(n) 个硬币凑齐；（明确循环体）
     * 3. 确定【选择】并择优
     *
     * f(n) = -1, n < 0
     *        0, n = 0
     *        dp(n-coin) +1, n > 0
     */

    /**
     * 暴力递归
     *
     * 时间复杂度：O(n^k)
     */
    private int coinCount(int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subp = coinCount(amount - coin);
            System.out.println("amount=" + amount + "; count=" + (subp+1));
            if (subp == -1) {
                continue;
            }
            res = Math.min(res, subp+1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 使用中断法，优化重叠子问题递归
     */
    private int dp(int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        for (int coin : coins) {
            int subp = dp(amount - coin);
            System.out.println("amount=" + amount + "; count=" + (subp+1));
            if (subp == -1) {
                continue;
            }
            return ++subp;
        }
        return -1;
    }

    Map<Integer, Integer> mapDp = Maps.newHashMap();

    /**
     * 使用备忘录，优化重叠子问题
     *
     * 时间复杂度：O(nk)
     */
    private int dp2(int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (mapDp.containsKey(amount)) {
            return mapDp.get(amount);
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subp = dp2(amount - coin);
            System.out.println("amount=" + amount + "; count=" + (subp+1));
            if (subp == -1) {
                continue;
            }
            res = Math.min(res, subp+1);
        }
        mapDp.put(amount, res);
        return res;
    }

    /**
     * dp table 优化重叠子问题
     *
     * 是根据最优子结构的原理：通过子问题的最优解可推导出问题的最优解
     */
    private int dp3(int amount) {
        mapDp.put(0, 0);
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (i < coin) {
                    continue;
                }
                mapDp.put(i, Math.min(mapDp.getOrDefault(i, Integer.MAX_VALUE), 1+mapDp.get(i-coin)));
            }
        }
        return mapDp.get(amount) == Integer.MAX_VALUE ? -1: mapDp.get(amount);
    }

    /**
     * 总结：
     *
     * 第一个，斐波那契数列的问题。
     * 解释了如何通过「备忘录」或者「dp table」的方法来优化递归树，并且明确了这两种方法本质上是一样的，只是自顶向下和自底向上的不同而已。
     *
     * 第二个，凑零钱的问题。
     * 展示了如何流程化确定「状态转移方程」，只要通过状态转移方程写出暴力递归解，剩下的也就是优化递归树，消除重叠子问题而已。
     *
     * 计算机解决问题其实没有任何奇技淫巧，它唯一的解决办法就是穷举，穷举所有可能性。
     * 算法设计无非就是先思考“如何穷举”，然后再追求“如何聪明地穷举”。
     *
     * 列出动态转移方程，就是在解决“如何穷举”的问题。
     *
     * 难点在于：
     * 一是因为很多穷举需要递归实现，
     * 二是因为有的问题本身的解空间复杂，不那么容易穷举完整。
     *
     * 备忘录、DP table 就是在追求“如何聪明地穷举”。用空间换时间的思路，是降低时间复杂度的不二法门。
     */
}
