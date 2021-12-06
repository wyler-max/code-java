package org.example.practice.practiceknowbox.controller;

import java.util.List;

import org.example.practice.practiceknowbox.common.CommonResponse;
import org.example.practice.practiceknowbox.services.RestTemplateService;
import org.example.practice.practiceknowbox.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateService restTemplateService;

    @GetMapping("/get")
    public String get(@RequestParam("sessionId") String sessionId) {
        String url = "https://qa3-mvip.knowbox.cn/user/center/info.do?sessionId=" + sessionId;
        String response = restTemplate.getForObject(url, String.class);
        org.example.practice.practiceknowbox.entities.CommonResponse kmsResponse =
            JSON.parseObject(response, org.example.practice.practiceknowbox.entities.CommonResponse.class);
        return kmsResponse.toString();
    }

    @GetMapping("/getV1")
    public String getV1() {
        String url =
            "https://qaaiclass.knowbox.cn/api/v2/competence/finish-status?studentId=1575903084377087&classIds=1673375700652543&sig=446e38eedcff533fc31f3b72d115324d";
        String response = restTemplate.getForObject(url, String.class);
        org.example.practice.practiceknowbox.entities.CommonResponse kmsResponse =
            JSON.parseObject(response, org.example.practice.practiceknowbox.entities.CommonResponse.class);
        return kmsResponse.toString();
    }

    @GetMapping("/getV2")
    public String getV2() {
        String url = "https://mvip.knowbox.cn/user/hello.do";
        ResponseEntity<CommonResponse<List<Long>>> response = restTemplate.exchange(url, HttpMethod.GET, null,
            new ParameterizedTypeReference<CommonResponse<List<Long>>>() {});
        return JsonUtil.toJson(response.getBody());
    }

    @GetMapping("/getN")
    public String getN(@RequestParam("value") Integer value) {
        String result = "";
        switch (value) {
            case 1:
                result = JsonUtil.toJson(restTemplateService.doGetForObject());
                break;
            case 11:
                result =
                    JsonUtil.toJson(restTemplateService.doGetForObjectV1("QQQQbVQsY49uD90uLQU8vGaaKKKKPkR5mODDBBQ"));
                break;
            case 12:
                result =
                    JsonUtil.toJson(restTemplateService.doGetForObjectV2("QQQQbVQsY49uD90uLQU8vGaaKKKKPkR5mODDBBQ"));
                break;
            case 2:
                result = JsonUtil.toJson(restTemplateService.doGetForEntity());
                break;
            case 3:
                result = JsonUtil.toJson(restTemplateService.doPostForObject());
                break;
            case 4:
                result = JsonUtil.toJson(restTemplateService.doRequestGeneric());
                break;
            default:
        }
        return result;
    }

    @GetMapping("/get/provider2/{id}")
    public String getProvider2(@PathVariable("id") Integer id) {
        String url = "http://127.0.0.1:8082/provider/user/get/" + id;
        // String url = "http://127.0.0.1:8082/provider/user/hello";
        return restTemplate.getForObject(url, String.class);
    }
}
