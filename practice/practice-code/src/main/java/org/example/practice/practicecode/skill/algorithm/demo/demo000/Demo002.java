package org.example.practice.practicecode.skill.algorithm.demo.demo000;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯
 */
public class Demo002 {

    List<List<Integer>> res = Lists.newLinkedList();
    List<String> resString = Lists.newLinkedList();

    /**
     * 回溯算法框架：
     *
     * 3个角色：
     *     1、路径：也就是已经做出的选择
     *     2、选择列表：也就是你当前可以做的选择
     *     3、结束条件：也就是到达决策树底层，无法再做选择的条件
     *
     * result = []
     * def backtrack(路径, 选择列表):
     *     if 满足结束条件:
     *         result.add(路径)
     *         return
     *
     *     for 选择 in 选择列表:
     *         做选择
     *         backtrack(路径, 选择列表)
     *         撤销选择
     *
     * 核心：递归调用之前「做选择」，在递归调用之后「撤销选择」
     *
     * 核心框架：
     * for 选择 in 选择列表:
     *     # 做选择
     *     将该选择从选择列表移除
     *     路径.add(选择)
     *     backtrack(路径, 选择列表)
     *     # 撤销选择
     *     路径.remove(选择)
     *     将该选择再加入选择列表
     */

    @Test
    public void demo() {
    }

    /**
     * 回溯demo--数组中数据的全排列。
     *
     * 构造一个决策树，路径为数字，每个节点都在做决策
     */
    @Test
    public void permute() {
        int[] nums = new int[]{1,2,3};
        LinkedList<Integer> track = Lists.newLinkedList();
        backTrack(nums, track);
        System.out.println(res);
    }

    /**
     * 回溯算法实现
     * 路径：记录在 track
     * 选择列表：nums 中不存在于 track 的元素
     * 结束条件：nums 中的元素全都在 track 中出现
     */
    private void backTrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        // 暴力循环
        for (int i = 0; i < nums.length; i++) {
            // 过滤掉不合法的选择
            if (track.contains(nums[i])) {
                continue;
            }
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树（递归）
            backTrack(nums, track);
            // 取消选择（移除当前选择，选择下一个情景）
            track.removeLast();
        }
    }

    /**
     * 回溯demo--N皇后问题
     *
     * 在一个 NxN 的棋盘上摆放 N个皇后，使其互相不能攻击。
     * PS：皇后的攻击方向为：上、下、左、右、左上、左下、右上、右下 8个方向
     */
    @Test
    public void solveNQueens() {
        int n = 4;
        // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
        String[][] board = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = "\"\"";
            }
        }
        nQuenBackTrack(board, 0);
        System.out.println(resString);
    }

    /**
     * 路径：board 中小于 row 的那些行都已经成功放置了皇后
     * 选择列表：第 row 行的所有列都是放置皇后的选择
     * 结束条件：row 超过 board 的最后一行
     */
    private void nQuenBackTrack(String[][] board, int row) {

        // 触发结束条件
        if (row >= board.length) {
            resString.add(Arrays.deepToString(board));
            return;
        }
        int n = board.length;
        for (int col = 0; col < n; col++) {
            // 排除不合法选择, 判断棋盘的 row 行 col 列是否有 Q
            if (!isValid(board, row, col)) {
                continue;
            }
            board[row][col] = "\"Q\"";
            nQuenBackTrack(board,row+1);
            board[row][col] = "\".\"";
        }
    }

    private boolean nQuenBackTrackOnce(String[][] board, int row) {

        // 触发结束条件
        if (row >= board.length) {
            resString.add(Arrays.deepToString(board));
            return true;
        }
        int n = board.length;
        for (int col = 0; col < n; col++) {
            // 排除不合法选择, 判断棋盘的 row 行 col 列是否有 Q
            if (!isValid(board, row, col)) {
                continue;
            }
            board[row][col] = "\"Q\"";
            if (nQuenBackTrackOnce(board,row+1)) {
                return true;
            }
            board[row][col] = "\".\"";
        }
        return false;
    }

    private boolean isValid(String[][] board, int row, int col) {
        int n = board.length;
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < n; i++) {
            if (board[i][col] == "\"Q\"") {
                return false;
            }
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {
            if (board[i][j] == "\"Q\"") {
                return false;
            }
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == "\"Q\"") {
                return false;
            }
        }
        return true;
    }
}
