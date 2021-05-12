package org.example.practicecode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 删除排序数组中的重复项
 *
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例：
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4
 */
public class Item26 {


    @Test
    public void demo() {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
    }

    // 双指针法 i: 旧索引，遍历；p: 新索引，目标数组
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int p = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                nums[++p] = nums[i];
            }
        }
        return ++p;
    }
}
