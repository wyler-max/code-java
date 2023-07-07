package org.example.practice.practiceknowbox.test;

import java.util.Map;

import org.example.practice.practiceknowbox.common.model.Response;
import org.example.practice.practiceknowbox.common.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

import javax.annotation.Resource;

@Service
public class RestTemplateService {

    @Resource(name = "simpleRestTemplate")
    private RestTemplate restTemplate;

    public String doGetForObject() {
        String url = "https://mvip.knowbox.cn/user/hello.do";
        return restTemplate.getForObject(url, String.class);
    }

    public String doGetForObjectV1(String sessionId) {
        String url = "https://mvip.knowbox.cn/user/center/info.do" + "?sessionId={s}";
        return restTemplate.getForObject(url, String.class, sessionId);
    }

    public String doGetForObjectV2(String sessionId) {
        Map<String, String> param = Maps.newHashMap();
        param.put("s", sessionId);
        String url = "https://mvip.knowbox.cn/user/center/info.do" + "?sessionId={s}";
        return restTemplate.getForObject(url, String.class, param);
    }

    public String doGetForEntity() {
        String url = "https://mvip.knowbox.cn/user/hello.do";
        ResponseEntity response = restTemplate.getForEntity(url, String.class);
        return String.valueOf(response.getBody());
    }

    public String doPostForObject() {
        String url = "https://mvip.knowbox.cn/user/hello.do";
        Map<String, Object> param = Maps.newHashMap();
        param.put("name", "jane");
        return restTemplate.postForObject(url, param, String.class);
    }

    /**
     * 1、定义header；2、定义泛型返回值
     */
    public Response doRequestGeneric() {
        String url = "https://mvip.knowbox.cn/user/hello.do";
        // 构造参数
        Map<String, Object> param = Maps.newHashMap();
        param.put("name", "jane");
        // 构造Headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("traceId", "randomValue");
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        // 构造请求实体
        HttpEntity<String> requestEntity = new HttpEntity<String>(JsonUtil.toJson(param), requestHeaders);
        ResponseEntity<Response<String>> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
            new ParameterizedTypeReference<Response<String>>() {});
        return response.getBody();
    }

    public String doRequestProvider2Hello() {
        String url = "http://localhost:8082/provider/user/hello";
        ResponseEntity response = restTemplate.getForEntity(url, String.class);
        return String.valueOf(response.getBody());
    }
}
