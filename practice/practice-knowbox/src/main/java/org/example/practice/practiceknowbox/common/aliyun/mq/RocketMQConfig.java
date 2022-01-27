package org.example.practice.practiceknowbox.common.aliyun.mq;

import java.util.Properties;

import lombok.Data;

/**
 * @author yijiu.chen
 * @date 2020/04/25
 */
@Data
public class RocketMQConfig {

    private String onsAddr;

    private String topic;

    private String accessKey;

    private String secretKey;

    private Properties producer;

    private Properties consumer;

}
