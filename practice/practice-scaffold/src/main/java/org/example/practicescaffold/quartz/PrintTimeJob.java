package org.example.practicescaffold.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.util.Random;

/**
 *  Quartz 基本组成：
 *
 *  Task(Job) / Scheduler / Trigger
 *
 * 依赖的jar包
 * <dependency>
 *     <groupId>org.quartz-scheduler</groupId>
 *     <artifactId>quartz</artifactId>
 *     <version>2.3.0</version>
 * </dependency>
 */
public class PrintTimeJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("PrintTimeJob start at: " + now + ", and salt is : Hello Job " + new Random().nextInt());
    }
}
