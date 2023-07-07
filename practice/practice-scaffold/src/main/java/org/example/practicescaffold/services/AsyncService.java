package org.example.practicescaffold.services;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author wangyulin
 * @date 2023/5/9
 */
@Service
@Slf4j
public class AsyncService {

    @Autowired
    @Lazy
    private AsyncService self;

    public void doSync() {
        log.info("同步");
    }

    @Async
    public void async() {
        log.info("异步common");
    }

    @Async
    public void doAsync() {
        log.info("异步");
        for (int i = 0; i < 5; i++) {
            self.async();
        }
    }

    @Async("defaultThreadPool")
    public void doAsync2() {
        log.info("异步2");
        System.out.println(MDC.get("X-B3-SpanId"));
        System.out.println(MDC.get("X-B3-ParentSpanId"));
        System.out.println(MDC.get("X-B3-TraceId"));
        System.out.println(MDC.get("X-B3-Sampled"));
        for (int i = 0; i < 5; i++) {
            self.async();
        }
    }
}
