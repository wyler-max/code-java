package org.example.practice.practiceknowbox.common.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.example.practice.practiceknowbox.common.aliyun.mq.AbstractMessageListener;
import org.example.practice.practiceknowbox.common.aliyun.mq.MessageConsumer;
import org.example.practice.practiceknowbox.common.model.Response;
import org.example.practice.practiceknowbox.common.util.IpUtil;
import org.example.practice.practiceknowbox.common.util.ResponseUtil;
import org.example.practice.practiceknowbox.common.util.SnowFlakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 健康检查
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
@RestController
@Slf4j
@RequestMapping("/innerapi/common/manager")
public class HealthController {
    @Autowired(required = false)
    private ApplicationInfoManager applicationInfoManager;

    @Autowired(required = false)
    private List<AbstractMessageListener> listeners;

    @Autowired(required = false)
    private MessageConsumer messageConsumer;

    @Autowired(required = false)
    private SnowFlakeIdGenerator snowFlakeIdGenerator;

    private volatile boolean appStarted = false;

    @EventListener(ApplicationReadyEvent.class)
    public void startUp() {
        log.info("app startup and status...");
        this.appStarted = true;
    }

    @RequestMapping(value = "/down", method = RequestMethod.GET)
    public Response<String> down(HttpServletRequest req) {
        if (! IpUtil.isSelfCall(req)) {
            return ResponseUtil.makeFail("DENY");
        }
        if (this.appStarted) {
            try {
                if (applicationInfoManager != null) {
                    applicationInfoManager.getInfo().setStatus(InstanceInfo.InstanceStatus.DOWN);
                }
                if (messageConsumer != null && !CollectionUtils.isEmpty(listeners)) {
                    for (AbstractMessageListener listener : listeners) {
                        log.info("unsubscribe topic:{}", listener.topic());
                        messageConsumer.unsubscribe(listener.topic());
                    }
                }
            } catch (Exception e) {
                log.error("HealthController down", e);
            }
            if (snowFlakeIdGenerator != null) {
                SnowFlakeIdGenerator.destory();
            }
            this.appStarted = false;
        }
        return ResponseUtil.makeSuccess("DOWN");
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String status(HttpServletResponse resp) {
        if (appStarted) {
            if (applicationInfoManager == null
                || InstanceInfo.InstanceStatus.UP == applicationInfoManager.getInfo().getStatus()) {
                return "OK";
            }
        }
        resp.setStatus(HttpStatus.NOT_FOUND.value());
        return StringUtils.EMPTY;
    }
}
