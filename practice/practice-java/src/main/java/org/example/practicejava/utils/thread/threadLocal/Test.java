package org.example.practicejava.utils.thread.threadLocal;

/**
 * @formatter:off
 * 测试两点：
 * 1、ThreadLocal 设置不同的值后取值变化
 * 2、在不同线程中 ThreadLocal 的编号
 */
public class Test {
    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        new Test().testTheadLocal();
    }

    public void testTheadLocal() throws InterruptedException {
        THREAD_LOCAL.set("main_thread");
        System.out.println("main_thread: " + Thread.currentThread().getName() + ", thread_local=" + THREAD_LOCAL.get());

        // todo 子线程
        new Thread(() -> {
            System.out.println("sub_thread: " + Thread.currentThread().getName() + ", thread_local=" + THREAD_LOCAL.get());

            System.out.println("设置子进程的 thread_local");
            THREAD_LOCAL.set("sub_thread");
            System.out.println("sub_thread: " + Thread.currentThread().getName() + ", thread_local=" + THREAD_LOCAL.get());
        }).start();

        Thread.sleep(3000);
        System.out.println("main_thread: " + Thread.currentThread().getName() + ", thread_local=" + THREAD_LOCAL.get());
    }

}
