package org.example.practicecode.algorithm.demo.demo000;

import org.junit.Test;

/**
 * 二分查找
 */
public class Demo003 {

    @Test
    public void demo() {
        int[] nums = new int[]{1,2,3,6,6,6,7,9,12};
        int res = rightBoundBinarySearch(nums, 6);
        System.out.println(res);
    }

    private static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;

        while (left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    private static int leftBoundBinarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid] == target) {
                // 锁定左侧边界
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 检查 left 越界情况
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    private static int rightBoundBinarySearch(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length -1;

        while (left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid] == target) {
                // 锁定右侧边界
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid -1;
            }
        }
        // 检查 right 越界情况
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }
}
