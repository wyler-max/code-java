package org.example.practice.consumer.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "server-provider", path = "/provider/user")
public interface ServerProviderUserApi {

    @GetMapping(value = "/get")
    String get();

    @PostMapping(value = "/post")
    String post();
}
