package org.example.practice.practicespring.feignclient;

import org.example.practice.practicespring.config.feign.FeignConfiguration2;
import org.example.practice.practicespring.config.feign.Provider2FeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用 server-provider2 服务
 * 
 * @author wangyulin
 */
@FeignClient(name = "server-provider2", path = "/provider", decode404 = false, fallback = Provider2FeignFallBack.class,
    configuration = FeignConfiguration2.class)
public interface Provider2Client {

    @GetMapping(value = "/hello")
    String hello();

    @GetMapping(value = "/delay")
    String delay(@RequestParam(value = "t") int t);
}
