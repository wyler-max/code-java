package org.example.practice.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * Feign 的三种 configuration配置，优先级
 *
 * application.properties > @EnableFeignClients.defaultConfiguration > @FeignClient.configuration
 *
 * 可配置项目： </br>
 * 1、日志等级 </br>
 * 2、Encoder/Decoder </br>
 * 3、
 */
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.NONE;
    }

    // 超时时间
    /*@Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }*/
}
