package org.example.practice.provider.controller;

import org.example.practice.commonutils.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/p/user")
@Slf4j
public class UserController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello provider";
    }

    @GetMapping(value = "/get/{id}")
    public User userGet(@PathVariable("id") Integer id) {
        return new User(id, "provider get User", 10);
    }

    @PostMapping(value = "/post")
    public User userPost(@RequestBody User user) {
        user.setUserName("provider post user");
        user.setAge(user.getAge() + 1);
        return user;
    }
}
