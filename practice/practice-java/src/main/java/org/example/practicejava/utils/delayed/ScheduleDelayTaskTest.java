package org.example.practicejava.utils.delayed;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Java 提供的 scheduled 相关api：ScheduledExecutorService、ThreadPoolTaskScheduler等
 *
 * 以某个频率⼀直循环执⾏延时任务
 */
public class ScheduleDelayTaskTest {

    /**
     * 使用 ScheduledExecutorService 执行定时任务
     */
    public static void scheduledExectorServiceTask() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        // 以某个频率⼀直循环执⾏延时任务
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行任务：" + "执行时间：" + new Date());
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    /**
     * 使用 ThreadPoolTaskScheduler 执行定时任务
     */
    public static void threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.initialize();
        taskScheduler.setPoolSize(1);
        // 以某个频率⼀直循环执⾏延时任务
        taskScheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行任务：task-1, " + "执行时间：" + new Date());
            }
        }, 2000L);
    }

    public static void main(String[] args) {
        System.out.println("程序启动");
        scheduledExectorServiceTask();
    }
}
