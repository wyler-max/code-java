package org.example.practice.consumer.controller;

import org.example.practice.commonutils.utils.JsonUtil;
import org.example.practice.consumer.entity.AbstractRequest;
import org.example.practice.consumer.entity.DefaultReq;
import org.example.practice.consumer.enums.TestEnum;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 抽象类不能实例化，因此无法直接作为传参的pojo使用
     */
    @PostMapping(value = "/test3")
    public String test3(@RequestBody AbstractRequest req) {
        return JsonUtil.toJson(req);
    }

    @PostMapping(value = "/test4")
    public String test4(@RequestBody DefaultReq req) {
        System.out.println(req.getToken());
        System.out.println(req.getName());
        System.out.println(req.getNumber());
        return JsonUtil.toJson(req);
    }
}
