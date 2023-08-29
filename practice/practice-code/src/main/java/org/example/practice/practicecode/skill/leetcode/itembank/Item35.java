package org.example.practice.practicecode.skill.leetcode.itembank;

import org.junit.Test;

/**
 * 删除排序数组中的重复项
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 示例：
 * 输入：nums = [1,3,5,6], target=5
 * 输出：2
 */
public class Item35 {


    @Test
    public void demo() {
        int[] nums = new int[]{1,3,5,6};
        int target = 0;
        int ret = searchInsert2(nums, target);
        System.out.println(ret);
    }

    /**
     * target in (out-left, in-left, in-mid-none, in-mid-has, in-right, out-right)
     *
     * nums = [1,3,5,6]
     * target in (0, 1, 2, 5, 6, 7)
     */
    // 暴力循环
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int i = 0;
        for (i = 0; i < len; i++) {
            if (target == nums[i]) {
                return i;
            }
            if (target < nums[i]) {
                return i;
            }
        }
        if (target > nums[len-1]) {
            return len;
        }
        return i-1;
    }

    // 二分查找 查找nums中第一个大于等于target的元素的位置
    public int searchInsert2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int left = 0, right = n-1, ret = n;
        while (left <= right) {
            int mid = ((right-left) >> 1) + left;
            if (target <= nums[mid]) {
                ret = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ret;
    }

}
