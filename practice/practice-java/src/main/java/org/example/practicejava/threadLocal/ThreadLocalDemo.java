package org.example.practicejava.threadLocal;


public class ThreadLocalDemo {

    /**
     * ThreadLocal变量，每个线程都有一个副本，互不干扰
     */
    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>();

    public static void main(String[] args) throws Exception {
        new ThreadLocalDemo().threadLocalTest();
    }

    public void threadLocalTest() throws Exception {
        THREAD_LOCAL.set("main_thread_value");
        String v = THREAD_LOCAL.get();
        System.out.println("Thread-0执行前，名称：" + Thread.currentThread().getName() + "线程得到的值：" + v);

        new Thread(new Runnable() {
            public void run() {
                String v = THREAD_LOCAL.get();
                System.out.println(Thread.currentThread().getName() + "线程得到的值：" + v);

                // 子线程中设置ThreadLocal值
                THREAD_LOCAL.set("sub_thread_value");
                v = THREAD_LOCAL.get();
                System.out.println("重新设置后：" + Thread.currentThread().getName() + "线程得到的值：" + v);
                System.out.println(Thread.currentThread().getName() + "线程执行结束");
            }
        }).start();

        Thread.sleep(3000L);
        v = THREAD_LOCAL.get();
        System.out.println("Thread-0执行后，名称：" + Thread.currentThread().getName() + "线程得到的值：" + v);
    }
}
