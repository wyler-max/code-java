package org.example.practice.practicespring.thread.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author wangyulin
 * @date 2023/5/9
 */
@Service
@Slf4j
public class AsyncService {

    public void doSync() {
        log.info("同步");
    }

    @Async
    public void doAsync() {
        log.info("异步");
    }

    @Async("defaultThreadPool")
    public void doAsync2() {
        log.info("异步2");
    }
}
