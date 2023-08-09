package org.example.practice.practicespring.services;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangyulin
 * @date 2023/7/17
 */
@Service
@Slf4j
public class RestTemplateService {

    @Resource(name = "simpleRestTemplate")
    private RestTemplate restTemplate;

    public String doRequestProvider1Delay(int t) {
        String url = "http://localhost:8081/provider/delay?t=" + t;
        ResponseEntity response = restTemplate.getForEntity(url, String.class);
        return String.valueOf(response.getBody());
    }

    public String doRequestKnowboxHello() {
        String url = "https://mvip.knowbox.cn/user/hello.do";
        ResponseEntity response = restTemplate.getForEntity(url, String.class);
        return String.valueOf(response.getBody());
    }
}
