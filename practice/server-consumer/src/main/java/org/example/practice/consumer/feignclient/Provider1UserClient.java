package org.example.practice.consumer.feignclient;

import org.example.practice.consumer.config.FeignConfiguration;
import org.example.practice.consumer.config.FeignConfiguration2;
import org.example.practice.consumer.config.Provider1UserFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "server-provider1", path = "/provider/user", decode404 = false,
    fallback = Provider1UserFeignFallBack.class, configuration = FeignConfiguration2.class)
public interface Provider1UserClient {

    @GetMapping(value = "/hello")
    String hello();
}
