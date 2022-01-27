package org.example.practice.practiceknowbox.common.util;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.math.NumberUtils;
import org.example.practice.practiceknowbox.common.cache.CacheKey;
import org.example.practice.practiceknowbox.common.cache.RedissonWrapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * copy from web <br>
 * 时间位调整，目前够17年用!!<br>
 * 目前支持相关id分片，最多64!!
 *
 * @author yijiu.chen
 * @date 2020/04/20
 */
@Slf4j
@Component
public class SnowFlakeIdGenerator implements InitializingBean {

    @Autowired(required = false)
    private RedissonWrapper redissonWrapper;

    // 初始时间(2021-11-18)
    private static final long INITIAL_TIME_STAMP = 1637164800000L;

    // 机器ID
    private static final long WORK_ID_BITS = 8L;

    // 改成相关id用
    private static final long DATACENTER_ID_BITS = 6L;

    // 机器最大数量
    private static final long RELATE_ID_SHARDING = 1L << DATACENTER_ID_BITS;

    // 最大数量
    private static final long MAX_WORKER_ID = ~(-1L << WORK_ID_BITS);

    // 数据中心最大值
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);

    // 序列号 1024，每秒100w
    private static final long SEQUENCE_BITS = 10L;

    private static final long WORKERID_OFFSET = SEQUENCE_BITS;

    private static final long DATACENTERID_OFFSET = WORK_ID_BITS + SEQUENCE_BITS;

    private static final long TIMESTAMP_OFFSET = DATACENTER_ID_BITS + WORK_ID_BITS + SEQUENCE_BITS;

    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    private long workerId;

    private long datacenterId;

    private long sequence = 0L;

    private long lastTimestamp = -1L;

    private Random random = new Random();

    public SnowFlakeIdGenerator() {}

    private SnowFlakeIdGenerator(long workerId, RedissonWrapper redissonWrapper) {
        if (workerId > MAX_WORKER_ID || workerId < 0L) {
            throw new IllegalArgumentException(
                String.format("WorkerID can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        log.info("SnowFlakeIdGenerator init with workId:{}", workerId);
        this.workerId = workerId;
        this.redissonWrapper = redissonWrapper;
    }

    public synchronized long nextId() {
        long timeStamp = System.currentTimeMillis();
        if (timeStamp < this.lastTimestamp) {
            throw new RuntimeException("The current time less than last time");
        }
        if (timeStamp == this.lastTimestamp) {
            this.sequence = (this.sequence + 1) & SEQUENCE_MASK;
            if (0 == this.sequence) {
                timeStamp = this.tillNextMillis(this.lastTimestamp);
            }
        } else {
            // 防止并发不够高从0开始的问题，导致取模不随机
            this.sequence = this.random.nextInt(64) & SEQUENCE_MASK;
        }
        this.lastTimestamp = timeStamp;

        return (timeStamp - INITIAL_TIME_STAMP) << TIMESTAMP_OFFSET | (this.datacenterId << DATACENTERID_OFFSET)
            | (this.workerId << WORKERID_OFFSET) | this.sequence;
    }

    /**
     * 绑定其它id，方便分片关系维护
     *
     * @param relateNumber
     * @return
     */
    public synchronized long nextId(long relateNumber) {
        long timeStamp = System.currentTimeMillis();
        if (timeStamp < this.lastTimestamp) {
            throw new RuntimeException("The current time less than last time");
        }
        if (timeStamp == this.lastTimestamp) {
            this.sequence = (this.sequence + 1) & SEQUENCE_MASK;
            if (0 == this.sequence) {
                timeStamp = this.tillNextMillis(this.lastTimestamp);
            }
        } else {
            // 防止并发不够高从0开始的问题，导致取模不随机
            this.sequence = this.random.nextInt(64) & SEQUENCE_MASK;
        }
        this.lastTimestamp = timeStamp;

        long tmpDatacenterId = relateNumber % RELATE_ID_SHARDING;
        return (timeStamp - INITIAL_TIME_STAMP) << TIMESTAMP_OFFSET | (tmpDatacenterId << DATACENTERID_OFFSET)
            | (this.workerId << WORKERID_OFFSET) | this.sequence;
    }

    public long findRelateIdLast(long number) {
        return (number >> DATACENTERID_OFFSET) & MAX_DATACENTER_ID;
    }

    private long tillNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    private static SnowFlakeIdGenerator DEFAULT_GENERATOR = null;

    private static long getDefaultWorkId() {
        Random rnd = new Random();
        try {
            NetworkInterface network = null;
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface nint = en.nextElement();
                if (!nint.isLoopback() && nint.getHardwareAddress() != null) {
                    network = nint;
                    break;
                }
            }
            if (network != null) {
                byte[] mac = network.getHardwareAddress();
                byte rndByte = (byte)(rnd.nextInt() & 0x000000FF);

                // take the last byte of the MAC address and a random byte as datacenter ID
                return (((0x000000FF & (long)mac[mac.length - 1]) | (0x0000FF00 & (((long)rndByte) << 8))) >> 6)
                    % MAX_WORKER_ID;
            }
        } catch (Exception e) {
            log.error("getDefaultWorkId", e);
        }
        return rnd.nextLong() % MAX_WORKER_ID;
    }

    public static long nextNumber() {
        return DEFAULT_GENERATOR.nextId();
    }

    public static long nextNumber(long relateNumber) {
        return DEFAULT_GENERATOR.nextId(relateNumber);
    }

    public static long findRelateNumberLast(long number) {
        long last = DEFAULT_GENERATOR.findRelateIdLast(number);
        if (last == 0L) {
            return RELATE_ID_SHARDING;
        }
        return last;
    }

    public static long findNextNumber(long number) {
        return DEFAULT_GENERATOR.nextId(SnowFlakeIdGenerator.findRelateNumberLast(number));
    }

    public static boolean destory() {
        try {
            WORKERD_EXPIRED_SCHEDULED.shutdown();
            return DEFAULT_GENERATOR.shutdown();
        } catch (Exception e) {
            log.warn("Stop ERROR", e);
        }
        return true;
    }

    /**
     * 自动分布式获取id
     */
    private static final ThreadFactory NAMED_THREAD_FACTORY =
        new ThreadFactoryBuilder().setNameFormat("SnowFlakeIdGenerator-redis-workerId-expired-%d").build();
    private static final ScheduledExecutorService WORKERD_EXPIRED_SCHEDULED =
        new ScheduledThreadPoolExecutor(1, NAMED_THREAD_FACTORY);

    @Override
    public void afterPropertiesSet() throws Exception {
        // 有配置，直接走配置
        long workerId = NumberUtils.toLong(System.getProperty("WORK_ID"), -1L);
        String hostIp = IpUtil.getHostIp();
        if (workerId > 0L && this.redissonWrapper != null) {
            this.redissonWrapper.setnx(this.buildKey(workerId), hostIp, DateUtil.ONE_MINUTE);
        }
        if (workerId < 0L && hostIp != null) {
            // REDIS
            workerId = this.fetchRedisWorkerId(hostIp);
        }
        if (workerId < 0L) {
            // random
            workerId = getDefaultWorkId();
            if (this.redissonWrapper != null) {
                this.redissonWrapper.setnx(this.buildKey(workerId), hostIp, DateUtil.ONE_MINUTE);
            }
        }
        SnowFlakeIdGenerator.DEFAULT_GENERATOR = new SnowFlakeIdGenerator(workerId, this.redissonWrapper);
        if (this.redissonWrapper != null) {
            String expireKey = this.buildKey(workerId);
            WORKERD_EXPIRED_SCHEDULED.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        boolean flag = SnowFlakeIdGenerator.this.redissonWrapper.expire(expireKey, DateUtil.ONE_MINUTE);
                        log.info("WORKERD_EXPIRED_SCHEDULED host:{}, expire {} result:{}", hostIp, expireKey, flag);
                    } catch (Exception e) {
                        log.error("expire key {} {}", expireKey, e);
                    }
                }
            }, 0L, DateUtil.TWENTY_SECONDS, TimeUnit.MILLISECONDS);
        }
    }

    private long fetchRedisWorkerId(String hostIp) {
        if (this.redissonWrapper == null) {
            return -1;
        }
        // 循环简单处理
        for (long i = MAX_WORKER_ID; i >= 0; i--) {
            if (this.redissonWrapper.setnx(this.buildKey(i), hostIp, DateUtil.ONE_MINUTE)) {
                return i;
            }
        }
        throw new RuntimeException("all redis workerId used!!");
    }

    private String buildKey(long workerId) {
        return CacheKey.buildKey("ALL", "SnowFlakeIdGenerator", String.valueOf(workerId));
    }

    private boolean shutdown() {
        if (this.redissonWrapper != null) {
            String key = this.buildKey(this.workerId);
            boolean flag = this.redissonWrapper.del(key);
            log.info("WORKERD_EXPIRED_SCHEDULED host:{}, delete {} result:{}", IpUtil.getHostIp(), key, flag);
        }
        return false;
    }
}
