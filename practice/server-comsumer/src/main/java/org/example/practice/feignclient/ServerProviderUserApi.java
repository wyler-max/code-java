package org.example.practice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "server-provider", path = "/provider/user")
public interface ServerProviderUserApi {

    @GetMapping(value = "/getUser")
    String get();

    @PostMapping(value = "/postUser")
    String post();
}
