package org.example.practicescaffold.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 */
@Component
@Slf4j
public class JedisUtils {

    @Autowired
    private JedisPool jedisPool;

    public static void releaseJedis(JedisPool jedisPool, Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    public String get(String key, int indexdb) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            value = jedis.get(key);
            log.info(value);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            releaseJedis(jedisPool, jedis);
        }
        return value;
    }

    public String set(String key, String value, int indexdb) {
        Jedis jedis = null;
        String ret = String.valueOf(0);
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            ret = jedis.set(key, value);
            log.info(ret);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            releaseJedis(jedisPool, jedis);
        }
        return ret;
    }

}
