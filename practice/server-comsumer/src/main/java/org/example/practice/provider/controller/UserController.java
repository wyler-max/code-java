package org.example.practice.provider.controller;

import javax.annotation.Resource;

import org.example.practice.provider.feignclient.KnowboxGatewayApi;
import org.example.practice.provider.feignclient.ServerProviderUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

    @Resource
    private ServerProviderUserApi serverProviderUserApi;
    @Autowired
    private KnowboxGatewayApi knowboxGatewayApi;

    @GetMapping(value = "/getComsumer")
    public String getComsumer() {
        return "this is comsumer, getComsumer";
    }

    @PostMapping(value = "/postComsumer")
    public String postComsumer() {
        return "this is comsumer, postComsumer";
    }

    @GetMapping(value = "/getProvider")
    public String getProvider() {
        return serverProviderUserApi.get();
    }

    @PostMapping(value = "/postProvider")
    public String postProvider() {
        return serverProviderUserApi.post();
    }

    @GetMapping(value = "/hello")
    public Object hello() {
        return knowboxGatewayApi.hello();
    }
}
