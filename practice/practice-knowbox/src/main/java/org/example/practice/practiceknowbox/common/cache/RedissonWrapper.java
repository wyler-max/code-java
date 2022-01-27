package org.example.practice.practiceknowbox.common.cache;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RBatch;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认都是string
 *
 * @author yijiu.chen
 * @date 2020/04/21
 */
@Slf4j
public class RedissonWrapper {

    private final RedissonClient redissonClient;

    public RedissonWrapper(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 删除
     */
    public boolean del(String key) {
        try {
            return redissonClient.getBucket(key).delete();
        } catch (Exception e) {
            log.error("RedissonWrapper del", e);
        }
        return false;
    }

    /**
     * 设置失效
     *
     * @param key
     * @param timeMillis
     * @return
     */
    public boolean expire(String key, long timeMillis) {
        try {
            return redissonClient.getBucket(key).expire(timeMillis, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("RedissonWrapper expire", e);
        }
        return false;
    }

    /**
     * 获取对象
     *
     * @param <T>
     * @param key
     * @return
     */
    public <T> T get(String key) {
        try {
            RBucket<T> bucket = redissonClient.getBucket(key);
            return bucket.get();
        } catch (Exception e) {
            log.error("RedissonWrapper get", e);
        }
        return (T)null;
    }

    /**
     * 设置
     *
     * @param <T>
     * @param key
     * @param obj
     */
    public <T> void set(String key, T obj) {
        try {
            RBucket<T> bucket = redissonClient.getBucket(key);
            bucket.set(obj);
        } catch (Exception e) {
            log.error("RedissonWrapper set", e);
        }
    }

    /**
     * 设置
     *
     * @param <T>
     * @param key
     * @param obj
     * @return
     */
    public <T> boolean setnx(String key, T obj) {
        try {
            RBucket<T> bucket = redissonClient.getBucket(key);
            return bucket.trySet(obj);
        } catch (Exception e) {
            log.error("RedissonWrapper setnx", e);
        }
        return false;
    }

    /**
     * 设置带时间
     *
     * @param <T>
     * @param key
     * @param obj
     * @param timeMillis
     */
    public <T> void setex(String key, T obj, long timeMillis) {
        try {
            RBucket<T> bucket = redissonClient.getBucket(key);
            bucket.set(obj, timeMillis, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("RedissonWrapper set", e);
        }
    }

    /**
     * 设置带时间
     *
     * @param <T>
     * @param key
     * @param obj
     * @param timeMillis
     */
    public <T> boolean setnx(String key, T obj, long timeMillis) {
        try {
            RBucket<T> bucket = redissonClient.getBucket(key);
            return bucket.trySet(obj, timeMillis, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("RedissonWrapper setnx with time", e);
        }
        return false;
    }

    /**
     * 批量获取
     *
     * @param <T>
     * @param keys
     * @return
     */
    public <T> Map<String, T> mget(String... keys) {
        try {
            return redissonClient.getBuckets().get(keys);
        } catch (Exception e) {
            log.error("RedissonWrapper mget", e);
        }
        return Maps.newHashMap();
    }

    /**
     * 批量设置
     *
     * @param <T>
     * @param valueMap
     * @return
     */
    public <T> boolean mset(Map<String, T> valueMap, long timeMillis) {
        if (valueMap == null || valueMap.size() == 0) {
            return false;
        }
        try {
            RBatch batch = redissonClient.createBatch();
            for (Entry<String, T> entry : valueMap.entrySet()) {
                batch.getBucket(entry.getKey()).setAsync(entry.getValue(), timeMillis, TimeUnit.MILLISECONDS);
            }
            batch.execute();
            return true;
        } catch (Exception e) {
            log.error("RedissonWrapper mset", e);
        }
        return false;
    }

    /**
     * 批量删除
     *
     * @param keys
     * @return
     */
    public long mdel(String... keys) {
        try {
            return redissonClient.getKeys().delete(keys);
        } catch (Exception e) {
            log.error("RedissonWrapper mdel", e);
        }
        return 0;
    }

    /**
     * 获取原始缓存对象
     *
     * @return
     */
    public RedissonClient redissonClient() {
        return this.redissonClient;
    }

}
