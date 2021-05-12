package org.example.practicejava.java8feature;


import org.junit.Test;

import java.util.Arrays;

public class UtilTest {

    int[] data = {4,12,1,3,5,7,9};

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
