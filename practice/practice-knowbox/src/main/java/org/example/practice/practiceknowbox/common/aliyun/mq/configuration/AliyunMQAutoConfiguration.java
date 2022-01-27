package org.example.practice.practiceknowbox.common.aliyun.mq.configuration;

import org.example.practice.practiceknowbox.common.aliyun.mq.RocketMQConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;


/**
 * @author yijiu.chen
 * @date 2020/04/25
 */
//@Configuration
public class AliyunMQAutoConfiguration {

    @Bean
    @ConfigurationProperties("aliyun.mq")
    public RocketMQConfig rocketMQConfig() {
        return new RocketMQConfig();
    }
}
