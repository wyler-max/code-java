package org.example.practice.provider2.controller;

import org.example.practice.commonutils.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/provider/user")
@Slf4j
public class UserController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello provider2";
    }

    @GetMapping(value = "/get/{id}")
    public User userGet(@PathVariable("id") Integer id) {
        // return new User(id, "provider2 get User", 10);
        // 模拟超时 2s
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new User(id, "provider2 get User", 10);
        // 模拟调用失败
        // throw new RuntimeException("provider2 调用失败");
    }

    @PostMapping(value = "/post")
    public User userPost(@RequestBody User user) {
        user.setUserName("provider2 post user");
        user.setAge(user.getAge() + 1);
        return user;
    }
}
