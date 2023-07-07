package org.example.practice.practicespring.domain.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.practice.practicespring.db.model.User;
import org.example.practice.practicespring.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyulin
 * @date 2021/9/17
 */
@RestController
@RequestMapping("/domain")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/queryById")
    public User queryById(@RequestParam("id") long id) {
        log.trace("log level trace");
        log.debug("log level debug");
        log.info("log level info");
        log.warn("log level warn");
        log.error("log level error");
        return userRepository.selectById(id);
    }

    @GetMapping(value = "/changePersonalName")
    public long changePersonalName(@RequestParam("id") long id, @RequestParam() String name) {
        User user = userRepository.selectById(id);
        if (user == null) {
            throw new IllegalStateException("用户不存在");
        }
        long count = userRepository.updateName(id, name);
        System.out.println(count);
        return count;
    }
}
