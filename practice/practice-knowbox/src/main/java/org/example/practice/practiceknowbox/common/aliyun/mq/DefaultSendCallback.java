package org.example.practice.practiceknowbox.common.aliyun.mq;

import org.slf4j.MDC;

import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;

import org.example.practice.practiceknowbox.common.web.interceptor.LogInterceptor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yijiu.chen
 * @date 2020/04/25
 */
@Slf4j
public class DefaultSendCallback implements SendCallback {
    private String requestId;

    public DefaultSendCallback(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public void onSuccess(SendResult sendResult) {
        MDC.put(LogInterceptor.REQUEST_ID, requestId);
        log.info("消息发送成功:  topic=" + sendResult.getTopic() + ", msgId=" + sendResult.getMessageId());
    }

    @Override
    public void onException(OnExceptionContext context) {
        MDC.put(LogInterceptor.REQUEST_ID, requestId);
        log.warn("消息发送失败: topic=" + context.getTopic() + ", msgId=" + context.getMessageId(), context.getException());
    }
}
