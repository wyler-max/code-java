package org.example.practice.consumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import feign.Logger;
import feign.Request;
import feign.Retryer;

/**
 * Feign 的三种 configuration配置，优先级从高到低 </br>
 * 1、在配置文件中application.properties > </br>
 * 2、在启动类中@EnableFeignClients.defaultConfiguration > </br>
 * 3、在客户端接口注解中@FeignClient.configuration
 *
 * 可配置项目： </br>
 * 1、日志等级 </br>
 * 2、Encoder/Decoder </br>
 */
@Configuration
public class FeignConfiguration {
    @Autowired
    private Environment environment;

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.NONE;
    }

    // 设置超时时间
    @Bean
    Request.Options options() {
        int ribbonReadTimeout = environment.getProperty("ribbon.ReadTimeout", int.class, 5000);
        int ribbonConnectionTimeout = environment.getProperty("ribbon.ConnectTimeout", int.class, 1000);
        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }

    // 设置重试次数 默认5次
    @Bean
    Retryer feignRetryer() {
        return new Retryer.Default();
    }
}
