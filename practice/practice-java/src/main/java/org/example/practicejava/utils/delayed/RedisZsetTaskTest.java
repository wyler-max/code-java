package org.example.practicejava.utils.delayed;

import java.io.Serializable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisZsetTaskTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public Object get(String key) {
        Object result = null;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key);
        } catch (Exception e) {
            log.info("------:getkey error{}", key);
        }

        return result;
    }

    @Test
    public void test() throws JsonProcessingException {
        String key = "name";
        Object o = redisTemplate.boundValueOps(key).get();
        /*
        String name = redisTemplate.boundValueOps(key).get();
        System.out.println("name=" + String.join(name));
        name = String.join("hahaha");
        System.out.println("name=" + String.join(name));
        redisTemplate.boundValueOps(key).set(name);*/
    }
}
