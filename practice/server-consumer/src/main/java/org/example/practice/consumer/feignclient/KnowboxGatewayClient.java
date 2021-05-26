package org.example.practice.consumer.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "apilive", url = "https://mvip.knowbox.cn")
public interface KnowboxGatewayClient {

    @GetMapping(value = "/user/hello.do")
    Object hello();
}
