package org.example.practice.practicecode.javalang.grammar.array;

import java.util.Arrays;

import org.junit.Test;

/**
 * 数组
 */
public class TestArray {

    @Test
    public void createIntArray() {
        // 方式一
        int[] arr = new int[3];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        System.out.println(Arrays.toString(arr));
        // 方式二
        int[] arr2 = new int[] {1, 2, 3};
        System.out.println(Arrays.toString(arr2));
        // 方式三
        int[] arr3 = {1, 2, 3};
        System.out.println(Arrays.toString(arr3));
    }
}
