package org.example.practicejava.utils.arrays;

import java.util.Arrays;

import org.junit.Test;

public class ArraysTest {

    int[] data = {4, 12, 1, 3, 5, 7, 9};

    @Test
    public void testUtilParallelSort() {
        Arrays.parallelSort(data);
        System.out.println(Arrays.toString(data));
    }

    @Test
    public void testUtilCollectParallel() {
        Arrays.parallelPrefix(data, Integer::sum);
        System.out.println(Arrays.toString(data));
    }
}
