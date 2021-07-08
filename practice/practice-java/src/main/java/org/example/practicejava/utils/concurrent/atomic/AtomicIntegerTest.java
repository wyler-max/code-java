package org.example.practicejava.utils.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class AtomicIntegerTest {

    private static int THREAD_COUNT = 20;
    private static volatile int count = 0;
    private static AtomicInteger aCount = new AtomicInteger(0);

    public static void main(String[] args) {
        testIncrease();
        testAtomicInteger();
    }

    private static void increaseAtomicInteger() {
        aCount.incrementAndGet();
    }

    private static void testAtomicInteger() {
        System.out.println("aCount=" + aCount);
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increaseAtomicInteger();
                }
            });
            threads[i].start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aCount=" + aCount);
    }

    private static void increase() {
        count++;
    }

    private static void testIncrease() {
        System.out.println("count=" + count);
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count=" + count);
    }
}
