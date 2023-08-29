package org.example.practice.practicecode.skill.leetcode.itembank;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

/**
 * 鸡蛋掉落问题：
 *
 * 有一栋从 1 到 N 共 N 层的楼，然后给你 K 个鸡蛋（K 至少为 1）。
 * 现在确定这栋楼存在楼层 0 <= F <= N，在这层楼将鸡蛋扔下去，鸡蛋恰好没摔碎（高于 F 的楼层都会碎，低于 F 的楼层都不会碎）。
 * 现在问你，最坏情况下，你至少要扔几次鸡蛋，才能确定这个楼层 F 呢？
 *
 * 解题思路：动态规划
 *
 */
public class Item887 {

    @Test
    public void demo() {
        int k = 2, n = 7;
        int ret = superEggDrop3(k, n);
        System.out.println(ret);
        /*for (int i = 0; i < k+1; i++) {
            for (int j = 0; j < n+1; j++) {
                String mapKey = i+"-"+j;
                if (map.containsKey(mapKey)) {
                    System.out.println(mapKey + "=" + map.get(mapKey));
                }
            }
        }*/
        //System.out.println(map);
    }

    /**
     * 状态转移伪代码
     */
    public int pseudoCode(int k, int n) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n+1; i++) {
            res = Math.min(res,
                    Math.max(
                            pseudoCode(k-1, i -1),
                            pseudoCode(k, n - i)
                    ));
        }
        return res;
    }

    Map<String, Integer> map = Maps.newHashMap();
    /**
     * 动态规划框架：
     * 1、这个问题有什么「状态」：剩余鸡蛋数量K，待测楼层高度N
     * 2、有什么「选择」 ：去哪层楼扔鸡蛋
     * 3、然后穷举：1 <= i <= n；特别地：k==1时，res=1;n==0是，res=0;
     *
     * 状态转移方程：
     * dp[k,n] = max(dp[k-1,i-1], dp[k,n-i])+1, k>1&n>0
     *           n,k=1
     *           0,n=0
     */
    private int superEggDrop(int k, int n) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        String mapKey = k+"-"+n;
        if (map.containsKey(mapKey)) {
            return map.get(mapKey);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < n+1; i++) {
            res = Math.min(res, Math.max(superEggDrop(k-1, i-1), superEggDrop(k, n - i))+1);
        }
        map.put(mapKey, res);
        return res;
    }

    /**
     * 动态规划+二分查找
     */
    private int superEggDrop2(int k, int n) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        String mapKey = k + "-" + n;
        if (map.containsKey(mapKey)) {
            return map.get(mapKey);
        }
        int res = Integer.MAX_VALUE;
        int low=1, high = n;
        // 用二分收缩代替线性搜索
        while (low <= high) {
            int mid = (low+high)/2;
            int case_broken = superEggDrop2(k-1, mid-1);
            int case_unbroken = superEggDrop2(k, n-mid);
            if (case_broken > case_unbroken) {
                high = mid-1;
                res = Math.min(res, case_broken+1);
            } else {
                low = mid+1;
                res = Math.min(res, case_unbroken+1);
            }
        }
        map.put(mapKey, res);
        return res;
    }

    /**
     * 高楼扔鸡蛋进阶
     */
    /**
     * 进阶伪代码
     *
     * 有 k 个鸡蛋，最多尝试 m 次，可锁定 n 层楼
     * dp[k][m] = n；（当k固定，随着m增加，dp[k][m] 是正向递增的↑）
     *
     * 状态转移方程：
     * 1、无论在哪层扔鸡蛋，鸡蛋总有两种状态，碎了或没碎；碎了就继续测楼下，没碎测楼上
     * 2、总楼层数 = 上楼 + 当前楼层（1） + 下楼
     *
     * 倒推二分法：
     * dp[k][m] = dp[k][m-1]（未碎）+ 1（当前） + dp[k-1][m-1]（碎了）
     */
    private int pseudoCode2(int k, int n) {
        int m = 0;
        int[][] dp = new int[k+1][n+1];
        while (dp[k][m] < n) {
            m ++;
            // 状态转移
        }
        return m;
    }

    /**
     * 时间复杂度：O(kn)
     */
    private int superEggDrop3(int k, int n) {
        int m = 0; // 允许次数的上边界
        int[][] dp = new int[k+1][n+1];
        while (dp[k][m] < n) {
            m ++;
            // 状态转移
            for (int i = 1; i < k+1; i++) {
                dp[i][m] = dp[i][m-1] + dp[i-1][m-1] + 1;
            }
        }
        return m;
    }
}
