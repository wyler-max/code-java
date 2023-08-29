package org.example.practice.practicecode.skill.leetcode.itembank;

import java.util.Arrays;

import org.junit.Test;

/**
 * 编辑距离问题：
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数
 *
 * 你可以对一个单词进行如下三种操作：
 *      插入一个字符
 *      删除一个字符
 *      替换一个字符
 *
 * 解题技巧：动态规划 (由已知状态推导出当前状态)
 *
 * 状态转移方程：
 * 单词1 word1 1...i，第 i 个字符为 A；单词2 word 1...j，第 j 个字符为 B
 * 用 d[i][j] 单词1 前 i 个字符 到 单词2 前 j 个字符的编辑距离
 *
 * 若 A = B
 * d[i][j] = min(d[i][j-1]+1, d[i-][j]+1, d[i-][j-])
 *         = 1 + min(d[i][j-1], d[i-1][j], d[i-1][j-1]-1)
 * 若 A ≠ B
 * d[i][j] = min(d[i][j-1]+1, d[i-][j]+1, d[i-][j-]+1)
 *         = 1 + min(d[i][j-1], d[i-][j], d[i-][j-])
 *
 */
public class Item72 {

    @Test
    public void demo() {
        String word1 = "HORSE";
        String word2 = "ROS";
        //System.out.println(minDistance(word1, word2));
        minDistance2(word1, word2);
    }

    /**
     *     H O R S E
     *   0 1 2 3 4 5
     * R 1 1 2 2 3 4
     * O 2 2 1 2 3 4
     * S 3 3 2 2 2 3
     */
    public int minDistance(String word1, String word2) {

        int n = word1.length(); // 作为列 5
        int m = word2.length(); // 作为行 3

        if (m * n == 0) {
            return m+n;
        }
        // n=5, m=3
        int[][] d = new int[n+1][m+1];

        // 初始化矩阵边缘
        for (int i = 0; i < m+1; i++) {
            d[0][i] = i;
        }
        for (int i = 0; i < n+1; i++) {
            d[i][0] = i;
        }
        // 构造 dp table
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                int left = d[i][j-1] + 1;
                int up = d[i-1][j] + 1;
                int left_up = d[i-1][j-1];
                if (word1.charAt(i-1) != word2.charAt(j-1)) {
                    left_up++;
                }
                d[i][j] = Math.min(Math.min(left, up), left_up);
            }
        }
        System.out.println(Arrays.deepToString(d));
        return d[n][m];
    }

    class Node {
        int val;
        String pos;
        Node before;

        public Node(int val, String pos, Node before) {
            this.val = val;
            this.pos = pos;
            this.before = before;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", pos='" + pos + '\'' +
                    ", before=" + before +
                    '}';
        }
    }

    /**
     *     R O S
     *   0 1 2 3
     * H 1 1 2 3
     * O 2 2 1 2
     * R 3 2 2 2
     * S 4 3 3 2
     * E 5 4 4 3
     *
     * 打印动态规划路径
     */
    private void minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // m行，n列
        Node[][] nodes = new Node[m+1][n+1];

        nodes[0][0] = new Node(0, "0,0", null);
        for (int i = 1; i < m+1; i++) {
            nodes[i][0] = new Node(i, i+",0", nodes[i-1][0]);
        }
        for (int i = 1; i < n+1; i++) {
            nodes[0][i] = new Node(i, "0,"+i,  nodes[0][i-1]);
        }
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                Node left = nodes[i][j-1];
                Node up = nodes[i-1][j];
                Node left_up = nodes[i-1][j-1];
                int left_up_val = nodes[i-1][j-1].val;
                if (word1.charAt(i-1) != word2.charAt(j-1)) {
                    left_up_val = left_up.val+1;
                }
                Node nodeNew = null;
                if (left.val < up.val && left.val < left_up_val) {
                    nodeNew = new Node(left.val+1, i+","+j, left);
                }
                if (up.val < left.val && up.val+1 < left_up_val) {
                    nodeNew = new Node(up.val+1,i+","+j, up);
                }
                if (nodeNew == null) {
                    nodeNew = new Node(left_up_val,i+","+j, left_up);
                }
                nodes[i][j] = nodeNew;
            }
        }
        for (int i = 0; i < m+1; i++) {
            for (int j = 0; j < n+1; j++) {
                System.out.print(nodes[i][j] + "\t");
            }
            System.out.println("\n");
        }
        System.out.println(nodes[m][n]);
    }

}
