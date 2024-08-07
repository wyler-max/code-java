package org.example.practice.practicespring.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 自定义线程池
 */
@Configuration
@EnableAsync
public class TaskConfig {

    /**
     * 初始化一个默认的线程池，beanName为taskExecutor，否则会因为找不到而自主创建一个
     * 
     * @return
     */
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数目
        executor.setCorePoolSize(16);
        // 指定最大线程数
        executor.setMaxPoolSize(64);
        // 队列中最大的数目
        executor.setQueueCapacity(16);
        // 线程名称前缀
        executor.setThreadNamePrefix("defaultThreadPool_");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        // 对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程空闲后的最大存活时间
        executor.setKeepAliveSeconds(60);
        // 加载
        executor.initialize();
        return executor;
    }

    /**
     * 初始化一个新的线程池
     * 
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor secondThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数目
        executor.setCorePoolSize(16);
        // 指定最大线程数
        executor.setMaxPoolSize(64);
        // 队列中最大的数目
        executor.setQueueCapacity(16);
        // 线程名称前缀
        executor.setThreadNamePrefix("secondThreadPool_");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        // 对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程空闲后的最大存活时间
        executor.setKeepAliveSeconds(60);
        // 加载
        executor.initialize();
        return executor;
    }
}
