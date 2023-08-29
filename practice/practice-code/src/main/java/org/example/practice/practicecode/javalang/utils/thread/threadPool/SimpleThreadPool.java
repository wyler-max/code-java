package org.example.practice.practicecode.javalang.utils.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {

    public static void main(String[] args) {
        // 创建了包含5个工作线程的固定大小线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // 向线程池提交10个任务
        for (int i = 0; i < 10; i++) {
            Runnable workerThread = new WorkerThread("" + i);
            executor.execute(workerThread);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all Threads");
    }
}
