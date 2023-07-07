package org.example.practice.practiceknowbox.controller;

import org.example.practice.practiceknowbox.common.util.JsonUtil;
import org.example.practice.practiceknowbox.test.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/test")
public class TestRestTemplateController {

    @Resource(name = "slowRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateService restTemplateService;

    @GetMapping("/finish-status")
    public String getV1() {
        String url =
            "https://qaaiclass.knowbox.cn/api/v2/competence/finish-status?studentId=1575903084377087&classIds=1673375700652543&sig=446e38eedcff533fc31f3b72d115324d";
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/get")
    public String getN(@RequestParam("value") Integer value) {
        String result = "";
        switch (value) {
            case 1:
                result = restTemplateService.doGetForObject();
                break;
            case 11:
                result = restTemplateService.doGetForObjectV1("QQQQbVQsY49uD90uLQU8vGaaKKKKPkR5mODDBBQ");
                break;
            case 12:
                result = restTemplateService.doGetForObjectV2("QQQQbVQsY49uD90uLQU8vGaaKKKKPkR5mODDBBQ");
                break;
            case 2:
                result = restTemplateService.doGetForEntity();
                break;
            case 3:
                result = restTemplateService.doPostForObject();
                break;
            case 4:
                result = JsonUtil.toJson(restTemplateService.doRequestGeneric());
                break;
            case 5:
                result = JsonUtil.toJson(restTemplateService.doRequestProvider2Hello());
                break;
            default:
        }
        return result;
    }
}
