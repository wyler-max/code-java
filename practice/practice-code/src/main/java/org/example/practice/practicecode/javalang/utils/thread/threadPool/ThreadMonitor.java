package org.example.practice.practicecode.javalang.utils.thread.threadPool;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadMonitor implements Runnable {

    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run = true;

    public ThreadMonitor(ThreadPoolExecutor executor, int seconds) {
        this.executor = executor;
        this.seconds = seconds;
    }

    public void shutdown() {
        this.run = false;
    }

    @Override
    public void run() {
        while (run) {
            System.out.println(
                String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                    this.executor.getPoolSize(), this.executor.getCorePoolSize(), this.executor.getActiveCount(),
                    this.executor.getCompletedTaskCount(), this.executor.getTaskCount(), this.executor.isShutdown(),
                    this.executor.isTerminated()));
            try {
                Thread.sleep(seconds * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
