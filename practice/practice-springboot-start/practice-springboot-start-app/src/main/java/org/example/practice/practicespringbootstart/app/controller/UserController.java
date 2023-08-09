package org.example.practice.practicespringbootstart.app.controller;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${config.env}")
    private String configEnv;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @GetMapping("/queryEnv")
    public String queryEnv() {
        StringBuilder rs = new StringBuilder();
        rs.append(configEnv).append("_").append(activeProfile);
        return rs.toString();
    }

    @GetMapping("/queryBydId")
    public String queryBydId() {
        return "abc";
    }
}
