package org.example.practicejava.utils.collection.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * ArrayList和LinkedList比较
 *
 * 一般来说：ArrayList查询快!LinkedList增删快
 * <p>
 * （1）元素新增性能比较
 * （2）元素获取比较：
 */
public class ListPerformanceTest {

    //迭代次数
    public static int ITERATION_NUM = 100000;

    /**
     * 新增性能比较：
     *
     * 理论上：ArrayList底层是数组实现，在动态扩容时，性能有所损耗，而LinkedList不存在数组扩容机制，所以LinkedList效率更高
     * 但实际可能是ArrayList更优一些
     */
    @Test
    public void testInsertPerformanceCompare() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Integer> linkedList = new LinkedList<>();
        List<Integer> arrayList = new ArrayList<>();

        System.out.println("LinkedList新增性能测试：");
        excuteListInsert(linkedList);

        System.out.println("ArrayList新增性能测试");
        excuteListInsert(arrayList);
    }

    /**
     * 获取性能比较：
     *
     * 由于LinkedList是链表结构，没有角标的概念，没有实现RandomAccess接口，不具备随机元素访问功能。
     * 所以在get方面表现的差强人意，ArrayList性能同样远优于LinkedList。
     */
    @Test
    public void testGetPerformanceCompare() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //填充ArrayList集合：
        List<Integer> arrayList = new ArrayList<>();
        for (int x = 0; x < ITERATION_NUM; x++) {
            arrayList.add(x);
        }

        //填充LinkedList集合：
        List<Integer> linkedList = new LinkedList<>();
        for (int x = 0; x < ITERATION_NUM; x++) {
            linkedList.add(x);
        }

        System.out.println("LinkedList获取性能测试：");
        excuteListGet(linkedList);

        System.out.println("ArrayList获取性能测试：");
        excuteListGet(arrayList);
    }

    private void excuteListInsert(List<Integer> list) {
        long start = System.nanoTime();
        for (int x = 0; x < ITERATION_NUM; x++) {
            list.add(x);
        }
        long end = System.nanoTime();
        System.out.println("插入总耗时：" + (end - start));
    }

    private void excuteListGet(List<Integer> list) {
        long start = System.nanoTime();
        for (int x = 0; x < ITERATION_NUM; x++) {
            list.get(x);
        }
        long end = System.nanoTime();
        System.out.println("获取总耗时：" + (end - start));
    }

    private void excuteListGet2(List<Integer> list) {
        Random random = new Random();
        long start = System.nanoTime();
        for (int x = 0; x < ITERATION_NUM; x++) {
            int i = random.nextInt();
            list.get(i);
        }
        long end = System.nanoTime();
        System.out.println("获取总耗时：" + (end - start));
    }
}
