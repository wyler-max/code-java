package org.example.practicejava.threadLocal;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo2 {

    /**
     * ThreadLocal变量，每个线程都有一个副本，互不干扰
     */
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>();

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
        new ThreadLocalDemo2().threadLocalTest();
    }

    public void threadLocalTest() throws Exception {



    }
}
