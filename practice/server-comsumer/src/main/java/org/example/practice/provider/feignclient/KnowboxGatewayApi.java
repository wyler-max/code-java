package org.example.practice.provider.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "apilive", url = "https://mvip.knowbox.cn")
public interface KnowboxGatewayApi {

    @GetMapping(value = "/user/hello.do")
    Object hello();
}