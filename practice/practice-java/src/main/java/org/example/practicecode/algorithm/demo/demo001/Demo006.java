package org.example.practicecode.algorithm.demo.demo001;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * KMP 算法
 *
 * KMP 算法（Knuth-Morris-Pratt 算法）是一个著名的字符串匹配算法，效率很高，但是复杂。
 *
 * 约定：用 pat 表示模式串，长度为 M，txt 表示文本串，长度为 N。
 * KMP 算法是在 txt 中查找子串 pat，如果存在，返回这个子串的起始索引，否则返回 -1。
 *
 * 读者见过的 KMP 算法应该是，一波诡异的操作处理 pat 后形成一个一维的数组 next，然后根据这个数组经过又一波复杂操作去匹配 txt。
 * 时间复杂度 O(N)，空间复杂度 O(M)。
 * 其实它这个 next 数组就相当于 dp 数组，其中元素的含义跟 pat 的前缀和后缀有关，判定规则比较复杂，不好理解。
 * 本文则用一个二维的 dp 数组（但空间复杂度还是 O(M)），重新定义其中元素的含义，使得代码长度大大减少，可解释性大大提高。
 *
 *
 *
 */
public class Demo006 {

    /**
     * KMP 算法 VS 暴力匹配算法：
     */

    /**
     * 暴力匹配
     *
     * 时间复杂度：O(mn)
     */
    private int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        if (M > N) {
            return -1;
        }
        for (int i = 0; i < N-M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (pat.charAt(j) != txt.charAt(i)) {
                    break;
                }
            }
            if (j == M) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 这样，当我们需要用同一 pat 去匹配不同 txt 时，就不需要浪费时间构造 dp 数组了
     *
     * pat: aaab
     * txt1: aaacaaab
     * txt2: aaaaaaab
     */
    private void kmpTemplate() {
        KMP kmp = new KMP("aaab");
        int pos1 = kmp.search("aaacaaab");
        int pos2 = kmp.search("aaaaaaab");
    }

    private int searchKMP(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        int[][] dp = new int[M][256];
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < N; i++) {
            // 当前是状态 j，遇到字符 txt[i]，
            // pat 应该转移到哪个状态？
            j = dp[j][txt.charAt(i)];
            // 如果达到终止态，返回匹配开头的索引
            if (j == M) {
                return i - M + 1;
            }
        }
        // 没到达终止态，匹配失败
        return -1;
    }

    /**
     * dp 数组的框架：
     * dp[j][c] = next 表示，当前是状态 j，遇到了字符 c，应该转移到状态 next。
     * for 0 <= j <= M:状态
     *      for 0 <= c <= 256:字符
     *          dp[j][c] = next
     *
     * 细化 dp 数组框架：
     * int X : 影子状态
     * for 0 <= j <= M:
     *      for 0 <= c <= 256:
     *          if c == pat[j]:
     *              # 状态推进
     *              dp[j][c] = j + 1;
     *          else:
     *              # 状态重启
     *              # 委托 X 计算重启位置
     *              dp[j][c] = dp[X][c]
     *
     */
    public class KMP {
        private int[][] dp;
        private String pat; // pat.length = M

        /**
         * pat=ababc
         * dp[0][a] = 1
         * dp[1][b] = 2
         * dp[2][a] = 3
         * dp[3][b] = 4
         * dp[4][c] = 5
         */
        public KMP(String pat) {
            this.pat = pat;
            // 通过 pat 构建 dp 数组
            // 需要 O(M) 时间
            int M = pat.length();
            int asciiLen = 256;
            dp = new int[M][asciiLen];
            // base case
            dp[0][pat.charAt(0)] = 1; // dp[0][a] = 1
            // 影子状态 X 初始 为 0
            int X = 0;
            // 当前状态 j 从 1 开始 到 M
            for (int j = 1; j < M; j++) {
                for (int c = 0; c < asciiLen; c++) {
                    dp[j][c] = dp[X][c];
                }
                dp[j][pat.charAt(j)] = j+1;
                // 更新影子状态
                X = dp[X][pat.charAt(j)];
            }
        }

        public int search(String txt) {
            // 借助 dp 数组去匹配 txt
            // 需要 O(N) 时间 txt.length = N
            int M = pat.length();
            int N = txt.length();
            int j = 0;
            for (int i = 0; i < N; i++) {
                j = dp[i][txt.charAt(i)];
                if (j == M) {
                    return i - M + 1;
                }
            }
            // 没到达终止态，匹配失败
            return -1;
        }
    }
}
