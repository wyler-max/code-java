package org.example.practice.consumer.config;

import org.example.practice.consumer.feignclient.Provider2UserClient;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Component
@Slf4j
public class Provider2UserFeignFallBack implements Provider2UserClient {

    private static final String FALLBACK_PREFIX = "fallback-";

    @Override
    public String hello() {
        return FALLBACK_PREFIX + "hello";
    }
}
