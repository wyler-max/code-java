package org.example.practicejava.utils.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class AtomicIntegerTest2 implements Runnable {

    private static int THREAD_COUNT = 20;
    private static volatile int count = 0;
    private static AtomicInteger aCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerTest2 atomicIntegerTest2 = new AtomicIntegerTest2();
        Thread thread1 = new Thread(atomicIntegerTest2);
        Thread thread2 = new Thread(atomicIntegerTest2);
        thread1.start();
        thread2.start();
        // join 是为了让 main 等待 thread1、thread2 子线程执行完毕
        thread1.join();
        thread2.join();
        System.out.println("aCount=" + aCount.get());
        System.out.println("count=" + count);
    }

    private void increaseAtomicInteger() {
        aCount.compareAndSet(0, 3);
    }

    private void increase() {
        count++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
            increaseAtomicInteger();
        }
    }
}
