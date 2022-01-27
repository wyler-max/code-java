package org.example.practice.practiceknowbox.common.aliyun.mq.configuration;

import java.util.List;
import java.util.Properties;

import org.example.practice.practiceknowbox.common.aliyun.mq.AbstractMessageListener;
import org.example.practice.practiceknowbox.common.aliyun.mq.MessageConsumer;
import org.example.practice.practiceknowbox.common.aliyun.mq.RocketMQConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import com.aliyun.openservices.ons.api.PropertyKeyConst;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yijiu.chen
 * @date 2020/04/26
 */
//@Configuration
@Slf4j
public class AliyunMQConsumerAutoConfiguration {

    @Autowired
    private RocketMQConfig config;

    @Autowired
    private List<AbstractMessageListener> listeners;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "aliyun.mq.consumer", value = "enabled", havingValue = "true")
    public MessageConsumer messageConsumer() {
        Properties properties = new Properties();
        log.info("执行consumer初始化……");
        properties.setProperty(PropertyKeyConst.GROUP_ID, config.getConsumer().getProperty("id"));
        properties.setProperty(PropertyKeyConst.AccessKey, config.getAccessKey());
        properties.setProperty(PropertyKeyConst.SecretKey, config.getSecretKey());
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, config.getOnsAddr());
        MessageConsumer consumer = new MessageConsumer(properties);
        for (AbstractMessageListener listener : listeners) {
            consumer.subscribe(listener.topic(), listener);
        }
        consumer.start();
        return consumer;
    }
}
