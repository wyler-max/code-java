package org.example.practice.practicespring.config.feign;

import org.example.practice.practicespring.feignclient.Provider1Client;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 当Feign接口调用失败时会调用指定fallback实现类
 *
 * fallback 是通过 Hystrix 实现的，所以需要开启 Hystrix，即 feign.hystrix.enabled=true
 *
 * 缺点：获取不到http请求的错误码和信息
 */
@Component
@Slf4j
public class Provider1FeignFallBack implements Provider1Client {

    private static final String FALLBACK_PREFIX = "fallback-";

    @Override
    public String hello() {
        return FALLBACK_PREFIX + "hello";
    }

    @Override
    public String delay(int t) {
        return FALLBACK_PREFIX + "hello";
    }
}
