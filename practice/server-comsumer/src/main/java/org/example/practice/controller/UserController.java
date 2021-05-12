package org.example.practice.controller;

import org.example.practice.feignclient.KnowboxGatewayApi;
import org.example.practice.feignclient.ServerProviderUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
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
