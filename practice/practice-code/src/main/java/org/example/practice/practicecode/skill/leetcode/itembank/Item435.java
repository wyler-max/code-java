package org.example.practice.practicecode.skill.leetcode.itembank;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

/**
 * 无重叠区间：
 *
 * 给定一个区间的集合，找到需要移动区间的最小数量，使剩余区间互不重叠
 * 注意：
 * 1、可以认为区间的终点总是大于起点
 * 2、区间[1,2]和[2,3]的边界相互 "接触"，但没有相互重叠
 *
 * 输入：[[1,2], [2,3], [3,4], [1,3]]
 * 输出：1
 * 解释：移除 [1,3] 后剩余空间没有重叠
 *
 * 解题思路：贪心算法
 *
 */
public class Item435 {

    @Test
    public void demo() {
        int[][] intvs = new int[][]{{1,3},{2,4},{4,6},{4,5},{5,7}};
        System.out.println(Arrays.deepToString(intvs));
        int ret = eraseOverlapIntervals(intvs);
        System.out.println(ret);
    }

    private int eraseOverlapIntervals(int[][] intvs) {
        int n = intvs.length;
        if (n <= 1) {
            return 0;
        }
        Arrays.sort(intvs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int count = 1;
        int x_end = intvs[0][1];
        for (int[] intv : intvs) {
            int start = intv[0];
            if (start >= x_end) {
                count++;
                x_end = intv[1];
            }
        }
        return n-count;
    }

}
