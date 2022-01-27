package org.example.practice.practiceknowbox.common.aliyun.mq;

import java.util.Properties;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.exception.ONSClientException;

/**
 * @author yijiu.chen
 * @date 2020/04/25
 */
public class MessageConsumer {

    private Consumer consumer;

    public MessageConsumer(Properties properties) {
        if (properties == null || properties.get(PropertyKeyConst.GROUP_ID) == null
            || properties.get(PropertyKeyConst.AccessKey) == null || properties.get(PropertyKeyConst.SecretKey) == null
            || properties.get(PropertyKeyConst.NAMESRV_ADDR) == null) {
            throw new ONSClientException("consumer properties not set properly.");
        }
        this.consumer = ONSFactory.createConsumer(properties);
    }

    public void start() {
        this.consumer.start();
    }

    public void shutdown() {
        if (this.consumer != null) {
            this.consumer.shutdown();
        }
    }

    public void subscribe(String topic, MessageListener messageListener) {
        consumer.subscribe(topic, "*", messageListener);
    }

    public void subscribe(String topic, String tag, MessageListener messageListener) {
        consumer.subscribe(topic, tag, messageListener);
    }

    public void unsubscribe(String topic) {
        consumer.unsubscribe(topic);
    }
}
