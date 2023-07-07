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
 * @date 2022/9/9
 */
@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {

    @GetMapping(value = "/level")
    public void queryById() {
        log.trace("log level trace");
        log.debug("log level debug");
        log.info("log level info");
        log.warn("log level warn");
        log.error("log level error");
    }

    public static void main(String[] args) {
        log.info("aaa {} bbb {}", "hello", null);
    }
}
