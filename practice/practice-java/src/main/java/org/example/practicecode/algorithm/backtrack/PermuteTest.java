package org.example.practicecode.algorithm.backtrack;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 回溯法，全排列
 */
public class PermuteTest {

    public static List<List<Integer>> res = new LinkedList<>();

    @Test
    public void permute() {
        int[] nums = new int[] {1, 2, 3};
        backtrack(nums, new LinkedList<>());
        System.out.println(res);
    }

    public static void backtrack(int[] nums, LinkedList<Integer> track) {
        if (nums.length == track.size()) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int num : nums) {
            // 已经选择过了
            if (track.contains(num)) {
                continue;
            }
            track.add(num);
            backtrack(nums, track);
            track.removeLast();
        }
    }
}
