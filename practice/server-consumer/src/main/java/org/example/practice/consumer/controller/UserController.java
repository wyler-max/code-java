package org.example.practice.consumer.controller;

import javax.annotation.Resource;

import org.example.practice.consumer.feignclient.KnowboxGatewayClient;
import org.example.practice.consumer.feignclient.Provider1UserClient;
import org.example.practice.consumer.feignclient.Provider2UserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试 Feign
 */
@RestController
@RequestMapping(value = "/consumer")
@Slf4j
public class UserController {

    @Resource
    private Provider1UserClient provider1UserClient;
    @Resource
    private Provider2UserClient provider2UserClient;
    @Resource
    private KnowboxGatewayClient knowboxGatewayClient;

    @GetMapping(value = "/provider1/user")
    public String provider1User() {
        return provider1UserClient.hello();
    }

    @GetMapping(value = "/provider2/user")
    public String provider2User() {
        return provider2UserClient.hello();
    }

    @GetMapping(value = "/hello")
    public Object hello() {
        return knowboxGatewayClient.hello();
    }
}
