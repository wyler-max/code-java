package org.example.practice.practiceknowbox.common.aliyun.mq.configuration;

import java.util.Properties;

import org.example.practice.practiceknowbox.common.aliyun.mq.MessageProducer;
import org.example.practice.practiceknowbox.common.aliyun.mq.RocketMQConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ProducerBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yijiu.chen
 * @date 2020/04/26
 */
//@Configuration
@Slf4j
public class AliyunMQProducerAutoConfiguration {

    @Autowired
    private RocketMQConfig config;

    @Bean(name = "producer", initMethod = "start", destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "aliyun.mq.producer", value = "enabled", havingValue = "true")
    public ProducerBean producer() {
        ProducerBean producerBean = new ProducerBean();
        Properties properties = new Properties();
        log.info("执行producer初始化……");
        properties.put(PropertyKeyConst.GROUP_ID, config.getProducer().getProperty("id"));
        properties.put(PropertyKeyConst.AccessKey, config.getAccessKey());
        properties.put(PropertyKeyConst.SecretKey, config.getSecretKey());
        properties.put(PropertyKeyConst.NAMESRV_ADDR, config.getOnsAddr());
        producerBean.setProperties(properties);
        return producerBean;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "aliyun.mq.producer", value = "enabled", havingValue = "true")
    public MessageProducer messageProducer(@Autowired ProducerBean producerBean) {
        MessageProducer messageProducer = new MessageProducer(producerBean);
        return messageProducer;
    }
}
