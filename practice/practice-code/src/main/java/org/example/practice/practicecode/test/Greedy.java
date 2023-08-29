package org.example.practice.practicecode.test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法
 */
public class Greedy {
    /**
     * 小朋友吃饼干
     * 小朋友：1 2
     * 饼干：1 2 4
     */
    static int solution1(int[] children, int[] cookies) {
        Arrays.sort(children);
        Arrays.sort(cookies);
        int child = 0;
        int cookie = 0;
        while (child < children.length && cookie < cookies.length) {
            if (cookies[cookie++] > children[child]) {
                child++;
            }
        }
        return child;
    }

    /**
     * 不重叠区间需要去掉区间最少的个数
     * [1,2] [1,3] [2,4]
     * 输入-二维数组
     * [[1,2],
     * [1,3],
     * [2,4]]
     */
    static int solution2(int[][] intVal) {
        Arrays.sort(intVal, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int count = 1;
        int preEnd = intVal[0][1];
        for (int i = 1; i < intVal.length; i++) {
            if (intVal[i][0] >= preEnd) {
                count++;
                preEnd = intVal[i][1];
            }
        }
        return intVal.length - count;
    }

    /**
     * 股票 貌似不太对
     */
    static int solution3(int[] stocks) {
        int profit = 0;
        for (int i = 0; i < stocks.length -1; i++) {
            if (stocks[i] < stocks[i+1]) {
                profit += stocks[i+1] - stocks[i];
            }
        }
        return profit;
    }

    /**
     * 股票-双指针法
     */
    static int solution4(int[] stocks) {
        int profit = 0;
        int p_in = 0;
        int p_out = 1;
        while (p_in <= p_out && p_out <= stocks.length-2) {
            if (stocks[p_out] > stocks[p_in]) {
                profit += stocks[p_out] - stocks[p_in];
                p_out +=2;
                p_in += 2;
            } else {
                p_out++;
                p_in++;
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        int[][] intVal = {{1,2},{1,3},{2,4}};
        int[] stocks = {7,1,5,6,7,4};
        System.out.println(solution4(stocks));
    }
}
