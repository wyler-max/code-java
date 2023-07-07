package org.example.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyulin
 * @date 2023/7/7
 */
@RestController
@RequestMapping(value = "/provider")
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello provider1";
    }

    @GetMapping(value = "/delay")
    public String delay(@RequestParam(value = "t", defaultValue = "0") int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "hello provider1";
    }
}
