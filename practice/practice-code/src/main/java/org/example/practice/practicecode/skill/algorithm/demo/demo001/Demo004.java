package org.example.practice.practicecode.skill.algorithm.demo.demo001;

import org.junit.Test;

import java.util.Arrays;

/**
 * 动态规划之博弈问题
 *
 * 石头游戏：
 * 你和你的朋友面前有一排石头堆，用一个数组 piles 表示，piles[i] 表示第 i 堆石子有多少个。
 * 你们轮流拿石头，一次拿一堆，但是只能拿走最左边或者最右边的石头堆。
 * 所有石头被拿完后，谁拥有的石头多，谁获胜。
 */
public class Demo004 {

    @Test
    public void demo() {
        int[] piles = new int[]{3, 9, 1, 2};
        int ret = stoneGame(piles);
        System.out.println(ret);
    }

    /**
     * piles = {3, 9, 1, 2}
     *
     * 思路：
     *  先手在做出选择之后，就成了后手，后手在对方做完选择后，就变成了先手。
     *  这种角色转换使得我们可以重用之前的结果，典型的动态规划标志。
     *
     * 先手选择：
     * dp[i][j].fir = max(piles[i]+dp[i+1][j].sec, piles[j]+dp[i][j-1].sec)
     * dp[i][j].fir = max(select_left + select_right)
     * 解释：我作为先手，面对 piles[i...j] 时有两种选择
     * 1、我 select left, 然后面对 piles[i+1...j] 成为后手继续选择
     * 2、我 select right,然后面对 piles[i...j-1] 成为后手继续选择
     *
     * 后手选择：
     * if select_left:
     *  dp[i][j].sec = dp[i+1][j].fir
     * if select right:
     *  dp[i][j].sec = dp[i][j-1].fir
     * 解释：我作为后手，等选手先选择，有两种选择
     * 1、先手 select left, 留下 piles[i+1][j], 轮到我，我成为先手
     * 2、先手 select right, 留下 piles[i][j-1], 轮到我，我成为先手
     *
     * 根据 dp table 我们可以找出 base case
     * dp[i][j].fir = piles[i]
     * dp[i][j].sec = 0
     * 其中 0<= i == j < n
     *
     * 我们可以通过 二维数组表示法 看到 base 是斜着的 piles = {3, 9, 1, 2}
     *
     * #    0       1       2       3
     * 0    3,0
     * 1            9,0
     * 2                    1,0
     * 3                            2,0
     * 所以需要斜着遍历数组
     *
     * 推导方向：
     * dp[i+1][j] + dp[i][j-1] => dp[i][j]
     *
     */
    private int stoneGame(int[] piles) {
        int n = piles.length;
        Pair[][] dp = new Pair[n][n];
        // 初始化 dp table
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }
        // 初始化 dp[i][i] 0,0 1,1 2,2 3,3
        for (int i = 0; i < n; i++) {
            dp[i][i].fir = piles[i];
            dp[i][i].sec = 0;
        }
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n-l; i++) {
                int j = i+l;
                // 选左边或选右边
                int left = piles[i] + dp[i+1][j].sec;
                int right = piles[j] + dp[i][j-1].sec;
                if (left > right) {
                    dp[i][j].fir = left;
                    dp[i][j].sec = dp[i+1][j].fir;
                } else {
                    dp[i][j].fir = right;
                    dp[i][j].sec = dp[i][j-1].fir;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        Pair res = dp[0][n-1];
        System.out.println(res);
        return res.fir - res.sec;
    }

    public class Pair {
        int fir, sec;

        public Pair(int fir, int sec) {
            this.fir = fir;
            this.sec = sec;
        }

        @Override
        public String toString() {
            return "("+fir+","+sec+")";
        }
    }
}
