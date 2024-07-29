package org.example.practice.practicespring.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/feign")
public class TestFeignController {

    @Autowired
    private KnowboxGatewayClient knowboxGatewayClient;

    @RequestMapping("/knowbox/hello")
    public String sync() {
        return knowboxGatewayClient.hello();
    }
}
