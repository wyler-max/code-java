package org.example.practice.consumer.controller;

import javax.annotation.Resource;

import org.example.practice.commonutils.pojo.User;
import org.example.practice.consumer.feignclient.KnowboxGatewayClient;
import org.example.practice.consumer.feignclient.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/consumer")
@Slf4j
public class UserController {

    @Resource
    private UserClient userClient;
    @Autowired
    private KnowboxGatewayClient knowboxGatewayClient;

    @GetMapping(value = "/get")
    public String get() {
        return "get consumer";
    }

    @PostMapping(value = "/post")
    public String post() {
        return "post consumer";
    }

    @GetMapping(value = "/provider/user/get/{id}")
    public User getProvider(@PathVariable("id") Integer id) {
        return userClient.userGet(id);
    }

    @PostMapping(value = "/provider/user/post")
    public User postProvider() {
        User user = new User(100, "comsumer post", 10);
        log.info(user.toString());
        return userClient.userPost(user);
    }

    @GetMapping(value = "/hello")
    public Object hello() {
        return knowboxGatewayClient.hello();
    }
}
