package org.example.practicejava.utils.thread.sync;

/**
 * 二、synchronized作用于静态方法
 *
 * 当synchronized修饰静态方法时，锁是class对象
 */
public class SynchronizedTest5 implements Runnable {
    // 共享资源
    static int i = 0;

    /**
     * synchronized 修饰实例方法
     */
    public static synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new SynchronizedTest5());
        Thread t2 = new Thread(new SynchronizedTest5());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
