package org.example.practice.practicespring.feign;

import org.example.practice.practicespring.feign.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wangyulin
 */
@FeignClient(name = "apilive", url = "https://mvip.knowbox.cn", configuration = FeignConfiguration.class)
public interface KnowboxGatewayClient {

    /**
     * 简单问候
     * 
     * @return
     */
    @GetMapping(value = "/user/hello.do")
    String hello();
}
