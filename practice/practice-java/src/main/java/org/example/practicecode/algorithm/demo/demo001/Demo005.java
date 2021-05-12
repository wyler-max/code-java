package org.example.practicecode.algorithm.demo.demo001;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 动态规划之贪心算法
 *
 * 贪心算法是动态规划的一个特例。
 * 简单说就是：每一步都做出一个局部最优的选择，最终的结果就是全局最优。（最优子结构）
 * 比如你面前放着 100 张人民币，只能拿十张，怎么才能拿最多的面额？显然每次选择剩下钞票中面值最大的一张，最后的选择一定是最优的
 *
 * 大部分问题明显不具备贪心选择性质，比如斗地主、石头游戏stoneGame、鸡蛋掉落问题等。
 *
 */
public class Demo005 {

    @Test
    public void demo() {
        int[][] intvs = new int[][]{{1,3},{2,4},{4,6},{4,5},{5,7}};
        System.out.println(Arrays.deepToString(intvs));
        int ret = intervalSchedule(intvs);
        System.out.println(ret);
    }

    /**
     * 区间调度问题：
     *
     * 举个例子，intvs = [[1,3], [2,4], [3,6]]，这些区间最多有 2 个区间互不相交，即 [[1,3], [3,6]]，算法应该返回 2
     *
     * 这个问题在生活中的应用广泛，比如你今天有好几个活动，每个活动都可以用区间 [start, end] 表示开始和结束的时间，请问你今天最多能参加几个活动呢？
     * 显然你一个人不能同时参加两个活动，所以说这个问题就是求这些时间区间的最大不相交子集
     *
     * # 0  1   2   3   4   5   6
     * 0    1  -   3
     * 1        2   -   4
     * 2            3   -   -   6
     * 3
     *
     */
    private int intervalSchedule(int[][] intvs) {
        int n = intvs.length;
        if (n == 0) {
            return 0;
        }
        // 根据右边界从小到大排序
        Arrays.sort(intvs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        System.out.println(Arrays.deepToString(intvs));
        int count = 1;
        int x_end = intvs[0][1];
        for (int[] interval: intvs) {
            int start = interval[0];
            if (start >= x_end) {
                count++;
                x_end = interval[1];
            }
        }
        return count;
    }
}
