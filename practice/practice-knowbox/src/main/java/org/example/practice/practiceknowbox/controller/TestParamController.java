package org.example.practice.practiceknowbox.controller;

import org.example.practice.practiceknowbox.common.util.JsonUtil;
import org.example.practice.practiceknowbox.dto.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/test")
public class TestParamController {

    @PostMapping("/doPost")
    public String getV1(@RequestBody UserInfo userInfo) {
        return JsonUtil.toJson(userInfo);
    }
}
