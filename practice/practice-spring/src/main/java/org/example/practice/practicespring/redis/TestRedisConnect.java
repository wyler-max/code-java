package org.example.practice.practicespring.redis;

import java.util.Map;

import javax.annotation.Resource;

import org.example.practice.commonutils.utils.JsonUtil;
import org.example.practice.practicespring.PracticeSpring;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试Redis连接
 */
@SpringBootTest(classes = PracticeSpring.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRedisConnect {

    @Resource
    RedisService redisService;

    @Test
    public void testString() {
        // string get/set/del
        String keyStr = "key:string";
        String strVal = redisService.getString(keyStr);
        redisService.set(keyStr, "value", 10);
        redisService.del(keyStr);
    }

    @Test
    public void testHash() {
        // hash hget/hset
        String keyHashKey = "key:hash";
        Object jack = redisService.hget(keyHashKey, "jack");
        String jackScore = redisService.hget(keyHashKey, "jack", String.class);
        System.out.println(jack);
        System.out.println(jackScore);

        redisService.hset(keyHashKey, "jack", "jjj", 10);
        redisService.hset(keyHashKey, "jane", "eee");
        jackScore = redisService.hget(keyHashKey, "jack", String.class);
        System.out.println(jackScore);
        Map<Object, Object> hmget = redisService.hmget(keyHashKey);
        System.out.println(JsonUtil.toJson(hmget));
    }
}
