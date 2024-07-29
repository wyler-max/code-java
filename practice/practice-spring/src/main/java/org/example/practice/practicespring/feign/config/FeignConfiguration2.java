package org.example.practice.practicespring.feign.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import feign.Logger;
import feign.Request;

@Component
public class FeignConfiguration2 {
    @Autowired
    private Environment environment;

    @Bean("feignLoggerLevel2")
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    // 设置超时时间
    @Bean("options2")
    Request.Options options() {
        int ribbonReadTimeout = environment.getProperty("ribbon.ReadTimeout", int.class, 6000);
        int ribbonConnectionTimeout = environment.getProperty("ribbon.ConnectTimeout", int.class, 1000);
        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }
}
