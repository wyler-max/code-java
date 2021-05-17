package org.example.practice.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/provider/user")
public class UserController {

    @GetMapping(value = "/get")
    public String getProvider() {
        return "this is provider, get user";
    }

    @PostMapping(value = "/post")
    public String postProvider() {
        return "this is provider, post user";
    }
}
