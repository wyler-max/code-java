package org.example.practice.consumer.controller;

import org.example.practice.consumer.enums.TestEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

    @RequestMapping(value = "/test1")
    public String test1() {
        for (TestEnum value : TestEnum.values()) {
            System.out.println(value);
        }
        return "success";
    }

    @RequestMapping(value = "/test2")
    public String test2(@RequestParam("param1") String param1, @RequestParam("param2") String param2) {
        System.out.println(TestEnum.formartDesc(TestEnum.VAR_10, param1, param2));
        return "success";
    }
}
