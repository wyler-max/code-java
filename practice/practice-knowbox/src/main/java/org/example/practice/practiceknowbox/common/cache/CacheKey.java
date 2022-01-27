package org.example.practice.practiceknowbox.common.cache;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.Assert;

import org.example.practice.practiceknowbox.common.util.DateUtil;
import lombok.Data;

/**
 * @author yijiu.chen
 * @date 2020/04/17
 */
@Data
public class CacheKey {
    public static final String SPLIT = "#";
    public static final long DEFAULT_EXPIRED_TIME = 5 * DateUtil.ONE_MINUTE;
    /**
     * homework 前缀
     */
    public static final String CACHE_PREFIX = "QYWX";

    private String key;
    private long expiredMillsTime;

    public CacheKey() {}

    public CacheKey(String cacheName) {
        String[] kv = StringUtils.split(cacheName, SPLIT);
        Assert.isTrue(kv.length == 1 || kv.length == 2, "缓存KEY错误");
        this.key = kv[0];
        if (kv.length == 2) {
            this.expiredMillsTime = NumberUtils.toLong(kv[1], DEFAULT_EXPIRED_TIME);
            if (this.expiredMillsTime <= 0L) {
                this.expiredMillsTime = DEFAULT_EXPIRED_TIME;
            }
        } else {
            this.expiredMillsTime = DEFAULT_EXPIRED_TIME;
        }
    }

    public static String buildKey(String appName, String cacheName, String key) {
        String cacheKey = String.format("%s:%s:%s:%s", CACHE_PREFIX, appName, cacheName, key);
        if (cacheKey.length() > 128) {
            cacheKey = DigestUtils.md5Hex(cacheKey);
        }
        return cacheKey;
    }
}
