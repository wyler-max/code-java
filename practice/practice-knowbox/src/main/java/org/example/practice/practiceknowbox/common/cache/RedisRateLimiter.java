package org.example.practice.practiceknowbox.common.cache;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <a href=
 * "https://github.com/redisson/redisson/wiki/6.-%E5%88%86%E5%B8%83%E5%BC%8F%E5%AF%B9%E8%B1%A1#612-%E9%99%90%E6%B5%81%E5%99%A8ratelimiter">限流器</a>
 *
 * @author zhangshuai
 * @date 2020/4/29 11:05 下午
 */
public class RedisRateLimiter {

    @Autowired
    private RedissonClient redissonClient;

    public void redisRateLimiter(String userId) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(userId);
        // 10分钟产生100个令牌，即允许100次请求
        rateLimiter.trySetRate(RateType.PER_CLIENT, 100, 10, RateIntervalUnit.MINUTES);
        // 申请令牌数
        rateLimiter.acquire(1);
        // 尝试获取令牌，超时放弃
        rateLimiter.tryAcquire(1, 1, TimeUnit.SECONDS);
    }
}
