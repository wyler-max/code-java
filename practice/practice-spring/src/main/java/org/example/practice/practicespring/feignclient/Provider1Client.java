package org.example.practice.practicespring.feignclient;

import org.example.practice.practicespring.config.feign.FeignConfiguration;
import org.example.practice.practicespring.config.feign.Provider1FeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用 server-provider1 服务
 * 
 * @author wangyulin
 */
@FeignClient(name = "server-provider1", path = "/provider", decode404 = true, fallback = Provider1FeignFallBack.class,
    configuration = FeignConfiguration.class)
public interface Provider1Client {

    @GetMapping(value = "/hello")
    String hello();

    @GetMapping(value = "/delay")
    String delay(@RequestParam(value = "t") int t);
}
