package org.example.practice.practicespring.controller;

import org.example.practice.practicespring.services.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试 Feign
 */
@RestController
@RequestMapping(value = "/restTemplate")
@Slf4j
public class RestTemplateController {

    @Autowired
    private RestTemplateService restTemplateService;

    @GetMapping(value = "/provider1/delay")
    public String provider1Delay(@RequestParam(value = "t", defaultValue = "0") int t) {
        return restTemplateService.doRequestProvider1Delay(t);
    }

    @GetMapping(value = "/provider2/delay")
    public String provider2Delay(@RequestParam(value = "t", defaultValue = "0") int t) {
        return "provider2Client.delay(t)";
    }

    @GetMapping(value = "/knowbox/hello")
    public String provider1Delay() {
        return restTemplateService.doRequestKnowboxHello();
    }
}
