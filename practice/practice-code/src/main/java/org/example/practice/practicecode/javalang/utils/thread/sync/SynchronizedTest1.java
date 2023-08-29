package org.example.practice.practicecode.javalang.utils.thread.sync;

/**
 * 一、synchronized作用于实例方法
 *
 * 1、多个线程访问同一个对象的同一个方法
 */
public class SynchronizedTest1 implements Runnable {
    // 共享资源
    static int i = 0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest1 test = new SynchronizedTest1();
        for (int j = 0; j < 5; j++) {
            new Thread(test).start();
        }
        Thread.sleep(500);
        System.out.println(i);
    }
}
