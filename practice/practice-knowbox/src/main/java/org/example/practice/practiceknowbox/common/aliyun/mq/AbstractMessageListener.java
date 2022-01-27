package org.example.practice.practiceknowbox.common.aliyun.mq;

import org.example.practice.practiceknowbox.common.web.interceptor.LogInterceptor;
import org.redisson.api.RedissonClient;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yijiu.chen
 * @date 2020/04/25
 */
@Slf4j
public abstract class AbstractMessageListener implements MessageListener {

    @Autowired
    private RedissonClient redissonClient;

    public abstract String topic();

    public abstract void handle(Message message, MessageEvent messageEvent);

    @Override
    public Action consume(Message message, ConsumeContext context) {
        MDC.put(LogInterceptor.REQUEST_ID, message.getKey());
        try {
            MessageEvent messageEvent = (MessageEvent)SerializationUtils.deserialize(message.getBody());
            log.debug("接收消息:[topic: {}, tag: {}, msgId: {}, startDeliverTime: {}]", message.getTopic(),
                message.getTag(), message.getMsgID(), message.getStartDeliverTime());
            this.handle(message, messageEvent);
            log.debug("handle message success.");
            return Action.CommitMessage;
        } catch (Exception e) {
            // 消费失败
            log.error("handle message fail, requeue it {}, {}", message.getTopic(), message.getMsgID(), e);
            return Action.ReconsumeLater;
        }
    }
}
