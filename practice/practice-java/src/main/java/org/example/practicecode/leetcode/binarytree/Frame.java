package org.example.practicecode.leetcode.binarytree;

/**
 *
 */
public class Frame {

    /**
     * 快速排序
     */
    public void quickSortFrame(int[] nums, int left, int right) {
        // 前序遍历，通过交换元素构建分界点 p
        int p = partition(nums, left, right);
        quickSortFrame(nums, left, p-1);
        quickSortFrame(nums, p+1, right);
    }

    public int partition(int[] nums, int left, int right) {
        return 0;
    }

    /**
     * 归并排序：即后续遍历或分治法
     */
    public void mergeSortFrame(int[] nums, int left, int right) {
        int mid = left + (right - left) >> 2;
        mergeSortFrame(nums, left, mid-1);
        mergeSortFrame(nums, mid+1, right);
        // 后序遍历，合并已排好序的部分
        merge(nums, left, mid, right);
    }

    public void merge(int[] nums, int left, int mid, int right) {
    }


}
