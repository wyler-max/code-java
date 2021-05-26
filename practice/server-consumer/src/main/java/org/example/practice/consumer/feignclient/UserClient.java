package org.example.practice.consumer.feignclient;

import org.example.practice.commonutils.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.util.JSONPObject;

@FeignClient(name = "server-provider", path = "/provider/user")
public interface UserClient {

    @GetMapping(value = "/get/{id}")
    JSONPObject userGet(@PathVariable("id") Integer id);

    @PostMapping(value = "/post")
    JSONPObject userPost(@RequestBody User user);
}