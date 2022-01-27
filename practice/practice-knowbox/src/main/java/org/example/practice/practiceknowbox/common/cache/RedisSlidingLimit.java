package org.example.practice.practiceknowbox.common.cache;

import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 滑窗实现 zSet, key:userId, score为时间戳
 *
 * @author zhangshuai
 * @date 2020/4/29 6:30 下午
 */
@Service
public class RedisSlidingLimit {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 起始时间是服务启动时间或者固定时间,2020-04-30 00:00:00
     */
    private static final long start = 1588176000000L;

    /**
     * @param key
     *            限流的维度
     * @param delta
     *            窗口大小
     * @param threshold
     *            窗口内允许的数量
     */
    public void limit(String key, int delta, long threshold) {
        // zSet结构
        RScoredSortedSet<Object> zSet = redissonClient.getScoredSortedSet(key);
        long now = System.currentTimeMillis() - start;
        long before = now - delta;
        zSet.removeRangeByScore(0, true, before, true);
        // 当前窗口内的数量
        int size = zSet.count(before, false, now, true);
        if (size > threshold) {
            // 当前请求过于频繁,阻塞或则返回异常
            return;
        }
        // 记录当前行为
        zSet.addScore(key, now);
    }

}
