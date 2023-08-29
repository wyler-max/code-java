package org.example.practice.practicecode.skill.algorithm.demo.demo001;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;
import java.util.Vector;

/**
 * 动态规划答疑：
 *
 * 1、到底什么才叫「最优子结构」，和动态规划什么关系。
 *      - 当最优子结构失效时，改造问题
 *
 * 2、为什么动态规划遍历 dp 数组的方式五花八门，有的正着遍历，有的倒着遍历，有的斜着遍历。
 *
 */
public class Demo002 {

    // 以下是伪代码

    /**
     * 最优子结构伪代码：
     * 1. 根据每个班的最高分求全校最高分，符合最优子结构
     * 2. 每个班都有最高分差，如何求得全校最大分差，改造问题，使用暴力递归
     *
     * 动态规划 dp 数组伪代码：
     * 1、正向遍历 i < n; j < n
     * 2、逆向遍历 i > 0; j > 0
     * 3、斜着遍历 i < n; j < n-l
     *
     * 把握两点：
     * 1.遍历过程中，所需的状态是已经计算出来的
     * 2.遍历的重点必须是存储结果的位置
     *
     */
}
