package org.example.practice.practicespring.feign.config;

import org.example.practice.practicespring.feign.Provider2Client;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Component
@Slf4j
public class Provider2FeignFallBack implements Provider2Client {

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
