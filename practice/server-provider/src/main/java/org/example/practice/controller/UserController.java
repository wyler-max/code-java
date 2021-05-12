package org.example.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/provider/user")
public class UserController {

    @GetMapping(value = "/getUser")
    public String getProvider() {
        return "this is provider, getUser";
    }

    @PostMapping(value = "/postUser")
    public String postProvider() {
        return "this is provider, postUser";
    }
}
