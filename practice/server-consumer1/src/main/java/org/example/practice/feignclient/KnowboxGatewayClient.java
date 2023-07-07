package org.example.practice.feignclient;

import org.example.practice.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wangyulin
 */
@FeignClient(name = "apilive", url = "https://mvip.knowbox.cn", configuration = FeignConfiguration.class)
public interface KnowboxGatewayClient {

    @GetMapping(value = "/user/hello.do")
    Object hello();
}
