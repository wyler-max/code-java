package org.example.practicejava.javaBase.sync;

/**
 * 2、一个线程获取了该对象的锁之后，其他线程来访问其他synchronized实例方法现象
 */
public class SynchronizedTest2 {
    public synchronized void method1() {
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public synchronized void method2() {
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public static void main(String[] args) {
        final SynchronizedTest2 test = new SynchronizedTest2();
        new Thread(test::method1).start();
        new Thread(test::method2).start();
    }
}
