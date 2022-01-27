package org.example.practice.practiceknowbox.common.aliyun.mq;

import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yijiu.chen
 * @date 2020/04/25
 */
@Slf4j
public class MessageProducer {

    private ProducerBean producer;

    public MessageProducer(ProducerBean producer) {
        this.producer = producer;
    }

    /**
     * 同步发送
     *
     * @param event
     * @return
     */
    public SendResult send(MessageEvent event) {
        log.info("start to send message. [topic: {}, tag: {}]", event.getTopic(), event.getTag());
        if (StringUtils.isEmpty(event.getTopic()) || null == event.getDomain()) {
            throw new RuntimeException("topic, or body is null.");
        }
        Message message =
            new Message(event.getTopic(), event.getTag(), event.getRequestId(), SerializationUtils.serialize(event));
        SendResult result = this.producer.send(message);
        log.info("send message success. ", result.toString());
        return result;
    }

    /**
     * 同步发送延时消息
     *
     * @param event
     * @param delay
     * @return
     */
    public SendResult send(MessageEvent event, long delay) {
        log.info("start to send message. [topic: {}, tag: {}]", event.getTopic(), event.getTag());
        if (StringUtils.isEmpty(event.getTopic()) || null == event.getDomain()) {
            throw new RuntimeException("topic, or body is null.");
        }
        Message message =
            new Message(event.getTopic(), event.getTag(), event.getRequestId(), SerializationUtils.serialize(event));
        message.setStartDeliverTime(System.currentTimeMillis() + delay);
        SendResult result = this.producer.send(message);
        log.info("send message success. ", result.toString());
        return result;
    }

    /**
     * 同步发送指定时间
     *
     * @param event
     * @param delay
     * @return
     */
    public SendResult fixedTimeSend(MessageEvent event, long deliverTime) {
        log.info("start to send message. [topic: {}, tag: {}]", event.getTopic(), event.getTag());
        if (StringUtils.isEmpty(event.getTopic()) || null == event.getDomain()) {
            throw new RuntimeException("topic, or body is null.");
        }
        Message message =
            new Message(event.getTopic(), event.getTag(), event.getRequestId(), SerializationUtils.serialize(event));
        message.setStartDeliverTime(deliverTime);
        SendResult result = this.producer.send(message);
        log.info("send message success. ", result.toString());
        return result;
    }

    /**
     * 单发
     *
     * @param event
     */
    public void sendOneway(MessageEvent event) {
        log.info("start to send message. [topic: {}, tag: {}]", event.getTopic(), event.getTag());
        if (StringUtils.isEmpty(event.getTopic()) || null == event.getDomain()) {
            throw new RuntimeException("topic, or body is null.");
        }
        Message message =
            new Message(event.getTopic(), event.getTag(), event.getRequestId(), SerializationUtils.serialize(event));
        this.producer.sendOneway(message);
        log.info("send message success. ");
    }

    public void sendAsync(MessageEvent event) {
        log.info("start to send message. [topic: {}, tag: {}]", event.getTopic(), event.getTag());
        if (StringUtils.isEmpty(event.getTopic()) || null == event.getDomain()) {
            throw new RuntimeException("topic, or body is null.");
        }
        Message message =
            new Message(event.getTopic(), event.getTag(), event.getRequestId(), SerializationUtils.serialize(event));
        this.producer.sendAsync(message, new DefaultSendCallback(event.getRequestId()));
    }

    public void sendAsync(MessageEvent event, long delay) {
        log.info("start to send message. [topic: {}, tag: {}]", event.getTopic(), event.getTag());
        if (StringUtils.isEmpty(event.getTopic()) || null == event.getDomain()) {
            throw new RuntimeException("topic, or body is null.");
        }
        Message message =
            new Message(event.getTopic(), event.getTag(), event.getRequestId(), SerializationUtils.serialize(event));
        message.setStartDeliverTime(System.currentTimeMillis() + delay);
        this.producer.sendAsync(message, new DefaultSendCallback(event.getRequestId()));
    }

    public void fixedTimeSendAsync(MessageEvent event, long deliverTime) {
        log.info("start to send message. [topic: {}, tag: {}]", event.getTopic(), event.getTag());
        if (StringUtils.isEmpty(event.getTopic()) || null == event.getDomain()) {
            throw new RuntimeException("topic, or body is null.");
        }
        Message message =
            new Message(event.getTopic(), event.getTag(), event.getRequestId(), SerializationUtils.serialize(event));
        message.setStartDeliverTime(deliverTime);
        this.producer.sendAsync(message, new DefaultSendCallback(event.getRequestId()));
    }
}
