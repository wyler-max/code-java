package org.example.practicescaffold.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Quartz 基本组成：
 *
 * Task(Job) / Scheduler / Trigger
 */
public class Client {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 1、创建调度器 scheduler
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();

        // 2、创建Job实例，并与具体Task(这里是PrintTimeJob)绑定
        JobDetail jobDetail = JobBuilder.newJob(PrintTimeJob.class).withIdentity("job1", "group1").build();

        // 3、创建Trigger实例
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .startNow()//立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)//每隔1s执行一次
                        .repeatForever())
                .build();//一直执行
        // 4、指定调度
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("------scheduler start !-------");
        scheduler.start();
        // 5、休眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("------scheduler shoutdown !-------");
    }
}
