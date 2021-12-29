package org.example.practicecode.leetcode.itembank;

import org.junit.Test;

/**
 * 移除元素
 *
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 示例：
 * 输入：nums = [3,2,2,3], val = 3,
 * 输出：返回新的长度 2, 并且 nums 中的前两个元素均为 2
 */
public class Item28 {


    @Test
    public void demo() {
        int[] nums = new int[]{3,2,2,3};
        int val = 3;
        System.out.println(removeElement(nums, val));
    }

    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[p++] = nums[i];
            }
        }
        return p;
    }
}
