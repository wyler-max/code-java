package org.example.practice.consumer.feignclient;

import org.example.practice.consumer.config.FeignConfiguration;
import org.example.practice.consumer.config.FeignConfiguration2;
import org.example.practice.consumer.config.Provider1UserFeignFallBack;
import org.example.practice.consumer.config.Provider2UserFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "server-provider2", path = "/provider/user", decode404 = false,
    fallback = Provider2UserFeignFallBack.class, configuration = FeignConfiguration2.class)
public interface Provider2UserClient {

    @GetMapping(value = "/hello")
    String hello();
}
