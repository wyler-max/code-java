package org.example.practice.practicespringbootstart.app.controller;

import org.example.practice.practicespringbootstart.biz.AlarmBizService;
import org.example.practice.practicespringbootstart.biz.UserBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyulin
 * @date 2023/8/9
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBizService userBizService;

    @GetMapping("/queryEnv")
    public String queryEnv() {
        return AlarmBizService.getActiveProfiles();
    }

    @GetMapping("/queryBydId")
    public String queryBydId() {
        return "abc";
    }
}
