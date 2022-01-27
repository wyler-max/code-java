package org.example.practice.practiceknowbox.common.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.NullValue;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yijiu.chen
 * @date 2020/04/17
 */
@Slf4j
public class RedissonCache implements Cache {
    private final String name;

    private final RedissonClient redissonClient;

    private final String appName;

    private final String cacheName;

    private final long expiredMillsTime;

    private final AtomicLong hits = new AtomicLong();

    private final AtomicLong puts = new AtomicLong();

    private final AtomicLong misses = new AtomicLong();

    public RedissonCache(String name, RedissonClient redissonClient, String appName, String cacheName,
        long expiredMillsTime) {
        this.name = name;
        this.redissonClient = redissonClient;
        this.appName = appName;
        this.cacheName = cacheName;
        this.expiredMillsTime = expiredMillsTime;
    }

    /* (non-Javadoc)
     * @see org.springframework.cache.Cache#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /* (non-Javadoc)
     * @see org.springframework.cache.Cache#getNativeCache()
     */
    @Override
    public Object getNativeCache() {
        return this.redissonClient;
    }

    /* (non-Javadoc)
     * @see org.springframework.cache.Cache#get(java.lang.Object)
     */
    @Override
    public ValueWrapper get(Object key) {
        Object value = this.redissonClient.getBucket(this.getKey(key)).get();
        if (value == null) {
            this.addCacheMiss();
        } else {
            this.addCacheHit();
        }
        return this.toValueWrapper(value);
    }

    /* (non-Javadoc)
     * @see org.springframework.cache.Cache#get(java.lang.Object, java.lang.Class)
     */
    @Override
    public <T> T get(Object key, Class<T> type) {
        RBucket<T> rBucket = this.redissonClient.getBucket(this.getKey(key));
        T value = rBucket.get();
        if (value == null) {
            this.addCacheMiss();
        } else {
            this.addCacheHit();
        }
        return value;
    }

    /* (non-Javadoc)
     * @see org.springframework.cache.Cache#get(java.lang.Object, java.util.concurrent.Callable)
     */
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        throw new RuntimeException("没实现");
    }

    /* (non-Javadoc)
     * @see org.springframework.cache.Cache#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public void put(Object key, Object value) {
        if (value != null) {
            this.redissonClient.getBucket(this.getKey(key)).set(value, this.expiredMillsTime, TimeUnit.SECONDS);
            this.addCachePut();
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.cache.Cache#putIfAbsent(java.lang.Object, java.lang.Object)
     */
    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        throw new RuntimeException("没实现");
    }

    /* (non-Javadoc)
     * @see org.springframework.cache.Cache#evict(java.lang.Object)
     */
    @Override
    public void evict(Object key) {
        boolean delete = this.redissonClient.getBucket(this.getKey(key)).delete();
        log.info("delete:{},result:{}", this.getKey(key), delete);
    }

    /* (non-Javadoc)
     * @see org.springframework.cache.Cache#clear()
     */
    @Override
    public void clear() {
        // do not support
    }

    protected Object toStoreValue(Object userValue) {
        if (userValue == null) {
            return NullValue.INSTANCE;
        }
        return userValue;
    }

    private String getKey(Object key) {
        return CacheKey.buildKey(appName, cacheName, key.toString().replaceAll("[\\p{Cntrl}]\\p{Space}]+", "_"));
    }

    private ValueWrapper toValueWrapper(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof NullValue) {
            return NullValue.INSTANCE;
        }
        return new SimpleValueWrapper(value);
    }

    /**
     * The number of get requests that were satisfied by the cache.
     *
     * @return the number of hits
     */
    long getCacheHits() {
        return hits.get();
    }

    /**
     * A miss is a get request that is not satisfied.
     *
     * @return the number of misses
     */
    long getCacheMisses() {
        return misses.get();
    }

    long getCachePuts() {
        return puts.get();
    }

    private void addCachePut() {
        puts.incrementAndGet();
    }

    private void addCacheHit() {
        hits.incrementAndGet();
    }

    private void addCacheMiss() {
        misses.incrementAndGet();
    }
}
