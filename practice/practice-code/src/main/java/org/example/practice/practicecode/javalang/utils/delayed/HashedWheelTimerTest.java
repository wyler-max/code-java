package org.example.practice.practicecode.javalang.utils.delayed;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;

/**
 * 时间轮 来自 netty-common包
 *
 * 分层时间轮</br>
 * 为了在大的延迟时长场景下节省时间轮的空间，可以使用类似时钟时、分、秒的多级单位定义分层时间轮。 </br>
 * 从高到低，每级时间轮都记录当前和下级时间轮的触发时间，当当前时间轮触发后新增一个下级时间轮，直到最后以及时间轮触发完成。
 */
@Slf4j
public class HashedWheelTimerTest {

    public static void main(String[] args) throws InterruptedException {

        DelayTask task1 = new DelayTask("task1");
        DelayTask task2 = new DelayTask("task2");
        DelayTask task3 = new DelayTask("task3");

        HashedWheelTimer timer = new HashedWheelTimer();
        timer.newTimeout(task1, 10, TimeUnit.SECONDS);
        timer.newTimeout(task2, 5, TimeUnit.SECONDS);
        timer.newTimeout(task3, 15, TimeUnit.SECONDS);

        int i = 1;
        while (true) {
            Thread.sleep(1000L);
            if (timer.pendingTimeouts() <= 0) {
                System.out.println("所有任务都已结束");
                break;
            } else {
                System.out.println("After " + i++ + "s");
            }
        }
    }

    /**
     * 延时任务实现 netty-common 的 TimerTask 接口
     */
    static class DelayTask implements TimerTask {

        String orderId;

        DelayTask(String orderId) {
            this.orderId = orderId;
        }

        // 过期后会回调该方法
        @Override
        public void run(Timeout timeout) throws Exception {
            System.out.println(orderId + "订单过期");
        }
    }

    static class DelayTaskLayer implements TimerTask {

        String orderId;
        int level; // 2-hour 1-minute 0-second
        int hour;
        int minute;
        int second;
        long timeout;

        DelayTaskLayer(String orderId, int hour, int minute, int second) {
            this.orderId = orderId;
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        // 过期后会回调该方法
        @Override
        public void run(Timeout timeout) throws Exception {
            switch (level) {
                case 2:

            }
            System.out.println(orderId + "订单过期");
        }
    }

    /**
     * 简单测试时间轮
     */
    @Test
    public void test() throws InterruptedException {
        // 倒计时锁，设置 n 个倒计时任务
        CountDownLatch countDownLatch = new CountDownLatch(2);

        // 定义一个HashedWheelTimer，有16个格的轮子，每一秒走一个一个格子
        HashedWheelTimer timer = new HashedWheelTimer(1, TimeUnit.SECONDS, 16);

        // 把任务加到HashedWheelTimer里，到了延迟的时间就会自动执行
        timer.newTimeout((timeout) -> {
            log.info("task1 execute");
            countDownLatch.countDown();
        }, 500, TimeUnit.MILLISECONDS);

        timer.newTimeout((timeout) -> {
            log.info("task2 execute");
            countDownLatch.countDown();
        }, 2, TimeUnit.SECONDS);
        countDownLatch.await();
        timer.stop();
    }

    // 定义 秒、时、分时间轮
    HashedWheelTimer second = new HashedWheelTimer(1, TimeUnit.SECONDS, 60);
    HashedWheelTimer minute = new HashedWheelTimer(1, TimeUnit.MINUTES, 60);
    HashedWheelTimer hour = new HashedWheelTimer(1, TimeUnit.HOURS, 24);

    @Test
    public void layered() {

        Date startDate = new Date();
        System.out.println("程序启动：" + startDate);

        // 计划 80s 后，即 1min20s 后执行任务
        Date taskDate = new Date(Instant.now().plusSeconds(80).toEpochMilli());
        System.out.println("计划执行时间：" + taskDate);

        DelayTask task1 = new DelayTask("task1");

        /*
        minute.newTimeout(task1, 1, TimeUnit.MINUTES);*/
    }

    public void addToTimeWheel(long second) {

    }
}
