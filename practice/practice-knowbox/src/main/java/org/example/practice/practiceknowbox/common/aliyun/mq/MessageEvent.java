package org.example.practice.practiceknowbox.common.aliyun.mq;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import org.example.practice.practiceknowbox.common.web.interceptor.LogInterceptor;
import lombok.Data;

/**
 * @author yijiu.chen
 * @date 2020/04/25
 */
@Data
public class MessageEvent implements Serializable {

    /**
     * 处理不了的异常次数
     */
    public static final int UN_HANDLER_EXCEPTION_RETRY_COUNT = 3;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 话题的名字
     */
    private String topic;

    /**
     * 话题的分类
     */
    private String tag;

    /**
     * 需要传递的领域对象
     */
    private MessageEventObject domain;

    /**
     * 链路requestId
     */
    private String requestId;

    public static MessageEvent of(String topic, String tag, MessageEventObject message) {
        MessageEvent event = new MessageEvent();
        event.domain = message;
        event.tag = tag;
        event.topic = topic;
        String requestId = MDC.get(LogInterceptor.REQUEST_ID);
        if (StringUtils.isBlank(requestId)) {
            requestId = DigestUtils.md5Hex(UUID.randomUUID().toString());
            MDC.put(LogInterceptor.REQUEST_ID, requestId);
        }
        event.requestId = requestId;
        return event;
    }

}
