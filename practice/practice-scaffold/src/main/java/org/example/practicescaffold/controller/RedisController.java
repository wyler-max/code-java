package org.example.practicescaffold.controller;

import java.util.Random;

import org.example.practicescaffold.config.redis.RedisService;
import org.example.practicescaffold.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/redis")
@RestController
@Slf4j
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping(value = "/getObject")
    public Object getObject(@RequestParam("key") String key) {
        return redisService.getObject(key, UserEntity.class);
    }

    @GetMapping(value = "/getString")
    public String getString(@RequestParam("key") String key) {
        return redisService.getString(key);
    }

    @GetMapping(value = "/setString")
    public String setString(@RequestParam("key") String key, @RequestParam("val") String val) {
        redisService.set(key, val);
        return "set success";
    }

    @GetMapping(value = "/setObject")
    public String setObject(@RequestParam("key") String key, @RequestParam("val") String val) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(val);
        userEntity.setNumber(new Random().nextInt(100));
        userEntity.setAge(new Random().nextInt(20));
        redisService.set(key, userEntity);
        return "set success";
    }
}
