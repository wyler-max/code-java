package org.example.practice.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/provider/user")
@Slf4j
public class UserController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello provider";
    }
}
