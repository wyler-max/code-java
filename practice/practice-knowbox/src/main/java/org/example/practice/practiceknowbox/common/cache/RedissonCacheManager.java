package org.example.practice.practiceknowbox.common.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.redisson.api.RedissonClient;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

import com.google.common.collect.Maps;

/**
 * 默认redis缓存管理，支持动态TTL，默认10分钟，redission默认是hash，改成string
 *
 * @author yijiu.chen
 * @date 2020/04/17
 */
public class RedissonCacheManager implements CacheManager {

    private final RedissonClient redissonClient;

    private final String appName;

    private Map<String, Cache> redissonCacheMap = Maps.newConcurrentMap();

    public RedissonCacheManager(RedissonClient redissonClient, String appName) {
        Assert.isTrue(redissonClient != null && appName != null, "redis和appName不能为空");
        this.redissonClient = redissonClient;
        this.appName = appName;
    }

    /* (non-Javadoc)
     * @see org.springframework.cache.CacheManager#getCache(java.lang.String)
     */
    @Override
    public Cache getCache(String name) {
        CacheKey key = new CacheKey(name);
        Cache cache = redissonCacheMap.get(key.getKey());
        if (cache != null) {
            return cache;
        }
        return createCache(key.getKey(), key.getExpiredMillsTime());
    }

    /* (non-Javadoc)
     * @see org.springframework.cache.CacheManager#getCacheNames()
     */
    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(redissonCacheMap.keySet());
    }

    private Cache createCache(String name, long expiredMillsTime) {
        Cache cache = new RedissonCache(name, redissonClient, appName, name, expiredMillsTime);
        Cache oldCache = redissonCacheMap.putIfAbsent(name, cache);
        if (oldCache != null) {
            cache = oldCache;
        }
        return cache;
    }

}
