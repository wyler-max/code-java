package org.example.springmvcstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1、入门案例
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println("Hello SpringMVC!");
        return "success";
    }
}
