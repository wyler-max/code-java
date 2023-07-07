package org.example.practice.controller;

import javax.annotation.Resource;

import org.example.practice.feignclient.KnowboxGatewayClient;
import org.example.practice.feignclient.Provider1Client;
import org.example.practice.feignclient.Provider2Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试 Feign
 */
@RestController
@RequestMapping(value = "/consumer")
@Slf4j
public class UserController {

    @Resource
    private Provider1Client provider1Client;
    @Resource
    private Provider2Client provider2Client;

    @Resource
    private KnowboxGatewayClient knowboxGatewayClient;

    @GetMapping(value = "/provider1/hello")
    public String provider1Hello() {
        return provider1Client.hello();
    }

    @GetMapping(value = "/provider1/delay")
    public String provider1Delay(@RequestParam(value = "t", defaultValue = "0") int t) {
        return provider1Client.delay(t);
    }

    @GetMapping(value = "/provider2/hello")
    public String provider2Hello() {
        return provider2Client.hello();
    }

    @GetMapping(value = "/provider2/delay")
    public String provider2Delay(@RequestParam(value = "t", defaultValue = "0") int t) {
        return provider2Client.delay(t);
    }

    @GetMapping(value = "/knowbox/hello")
    public Object knowboxHello() {
        return knowboxGatewayClient.hello();
    }
}
