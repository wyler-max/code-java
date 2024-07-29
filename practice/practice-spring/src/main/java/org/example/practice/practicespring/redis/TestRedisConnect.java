package org.example.practice.practicespring.redis;

import java.util.Map;

import javax.annotation.Resource;

import org.example.practice.practicespring.PracticeSpring;
import org.example.practice.practicespring.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试Redis连接
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PracticeSpring.class)
public class TestRedisConnect {

    @Resource
    RedisService redisService;

    @Test
    public void testString() {
        String key = "key:string";
        String val = redisService.getString(key);
        System.out.println(val);

        redisService.set(key, "valNew", 10);
        String valNew = redisService.getString(key);
        System.out.println(valNew);

        redisService.del(key);
    }

    @Test
    public void testHash() {
        String keyHashKey = "key:hash";
        Object jack = redisService.hget(keyHashKey, "k1");
        String jackScore = redisService.hget(keyHashKey, "k1", String.class);
        System.out.println(jack);
        System.out.println(jackScore);

        redisService.hset(keyHashKey, "k1", "v1", 10);
        redisService.hset(keyHashKey, "k2", "v2");
        jackScore = redisService.hget(keyHashKey, "k1", String.class);
        System.out.println(jackScore);
        Map<Object, Object> hmget = redisService.hmget(keyHashKey);
        System.out.println(JsonUtil.toJson(hmget));
    }
}
