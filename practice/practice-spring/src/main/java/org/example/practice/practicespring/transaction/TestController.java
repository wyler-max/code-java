package org.example.practice.practicespring.transaction;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/{testN}")
    public int test(@PathVariable("testN") String testN, @RequestParam(value = "username", required = false) String username) throws IOException {
        switch (testN) {
            case "test1":
                return userService.insertUser1(username);
            case "test2":
                return userService.insertUser2(username);
            case "test3":
                return userService.insertUser3(username);
            case "test4":
                return userService.insertUser4(username);
            case "test5":
                return userService.insertUser5(username);
            case "test51":
                return userService.insertUser51(username);
            case "test6":
                return userService.insertUser6(username);
            case "test61":
                return userService.insertUser61(username);
            case "test7":
                return userService.insertUser7(username);
            case "test8":
                return userService.insertUser8(username);
            case "test81":
                return userService.insertUser81(username);
            case "test9":
                return userService.insertUser9(username);
            case "test10":
                return userService.insertUser10(username);
            case "test11":
                return userService.insertUser11(username);
            default:
                return -1;
        }
    }
}
