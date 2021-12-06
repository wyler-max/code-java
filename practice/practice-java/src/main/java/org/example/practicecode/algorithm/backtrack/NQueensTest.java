package org.example.practicecode.algorithm.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * N皇后问题
 * @formatter:off
 */
public class NQueensTest {

    public static int queenNum = 8;
    public static List<List<String>> board = new LinkedList<>();

    /**
     * modeling：定义棋盘 board、行 row、列 col；
     * board = new LinkList<LinkList<String>> ();
     * 棋盘上，空白处表示为 "."，放置皇后表示为 "Q"
     */
    public void nQueens() {

    }

    /**
     * 路径：board中小于row的行都已经放置了皇后
     * 选择列表：第row行，所有的col都是放置皇后的选择（1、选择必须合法；2、col∈(1,n)）
     * 结束条件：row>board.now.size
     */
    public static void backtrack(List<String> board, int row) {
        //if (row > board.size())
    }
}
