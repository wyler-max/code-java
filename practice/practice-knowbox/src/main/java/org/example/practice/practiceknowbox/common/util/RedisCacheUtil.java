package org.example.practice.practiceknowbox.common.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.example.practice.practiceknowbox.common.cache.RedissonWrapper;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yijiu.chen
 * @date 2020/05/06
 */
@Slf4j
public class RedisCacheUtil {

    /**
     * 批量取缓存数据或者从原始数据源取
     *
     * @param <T>
     * @param redis
     * @param keys
     * @return
     */
    public static <K, T> Map<K, T> batchCacheOrOriginalQuery(RedissonWrapper redis, List<K> keys,
                                                             OriginalFetcher<K, T> fetcher) {
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptyMap();
        }
        int size = keys.size();
        String[] cacheKeys = new String[size];
        for (int i = 0; i < size; i++) {
            cacheKeys[i] = fetcher.buildKey(keys.get(i));
        }
        Map<String, T> cachedMap = redis.mget(cacheKeys);
        List<K> unCachedKeys = Lists.newArrayListWithExpectedSize(size);
        Map<K, T> resultMap = Maps.newHashMapWithExpectedSize(size);
        for (K key : keys) {
            T value = cachedMap.get(fetcher.buildKey(key));
            if (value == null) {
                unCachedKeys.add(key);
            } else {
                resultMap.put(key, value);
            }
        }
        int originalFetchSize = 0;
        if (unCachedKeys.size() > 0) {
            List<T> unCachedValues = fetcher.originalFetch(unCachedKeys);
            if (!CollectionUtils.isEmpty(unCachedValues)) {
                originalFetchSize = unCachedKeys.size();
                Map<String, T> needCacheMap = Maps.newHashMapWithExpectedSize(unCachedKeys.size());
                for (T value : unCachedValues) {
                    String key = fetcher.buildKey(fetcher.getKeyValue(value));
                    resultMap.put(fetcher.getKeyValue(value), value);
                    needCacheMap.put(key, value);
                }
                redis.mset(needCacheMap, fetcher.cacheTime());
            }
        }
        log.debug("batchCacheOrOriginalQuery total:{}, cached:{}, originalFetch:{}", keys.size(), cachedMap.size(),
            originalFetchSize);

        return resultMap;
    }

    /**
     * 默认缓存操作
     *
     * @param redis
     * @param key
     * @param fetcher
     * @param <K>
     * @param <T>
     * @return
     */
    public static <K, T> T cacheOrOriginalQuery(RedissonWrapper redis, K key, OriginalFetcher<K, T> fetcher) {
        if (key == null) {
            return null;
        }
        String cacheKey = fetcher.buildKey(key);
        T cacheValue = redis.get(cacheKey);
        if (cacheValue != null) {
            return cacheValue;
        }

        List<T> originalValues = fetcher.originalFetch(Lists.newArrayList(key));
        if (CollectionUtils.isEmpty(originalValues)) {
            return null;
        }

        T originalValue = originalValues.get(0);
        redis.setnx(cacheKey, originalValue, fetcher.cacheTime());
        return originalValue;
    }

    public static interface OriginalFetcher<K, T> {

        List<T> originalFetch(List<K> keys);

        String buildKey(K key);

        K getKeyValue(T value);

        default long cacheTime() {
            return DateUtil.HALF_HOUR;
        }
    }
}
