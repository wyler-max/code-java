package org.example.practicecode.algorithm.demo.demo000;

import org.junit.Test;

import java.util.Arrays;

/**
 * 左右指针技巧：
 *  left=0, right=nums.length-1
 */
public class Demo006 {

    @Test
    public void demo1() {
        int[] nums = new int[]{1,2,3,6,7,9,12};
        int ret = binarySearch(nums, 6);
        System.out.println(ret);
    }

    /**
     * 左右指针，二分查找
     */
    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while (left <= right) {
            int mid = (left+right)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid -1;
            } else if (nums[mid] < target) {
                left = mid +1;
            }
        }
        return -1;
    }

    @Test
    public void demo2() {
        int[] nums = new int[]{2,7,11,15};
        int[] ret = twoSum(nums, 9);
        System.out.println(Arrays.toString(ret));
    }

    /**
     * 左右指针，查找两个数之和等于目标值
     */
    private int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    @Test
    public void demo3() {
        int[] nums = new int[]{2,7,11,15};
        int[] ret = reverse2(nums);
        System.out.println(Arrays.toString(ret));
    }

    private int[] reverse(int[] nums) {
        int left = 0, right = nums.length -1;
        int[] ret = new int[nums.length];
        while (left < right) {
            ret[left] = nums[right];
            ret[right] = nums[left];
            left++;
            right--;
        }
        if (left == right) {
            ret[left] = nums[left];
        }
        return ret;
    }

    private int[] reverse2(int[] nums) {
        int left = 0, right = nums.length -1;
        while (left < right) {
            nums[left] = nums[left] ^ nums[right];
            nums[right] = nums[left] ^ nums[right];
            nums[left] = nums[left] ^ nums[right];
            left++;
            right--;
        }
        return nums;
    }
}
