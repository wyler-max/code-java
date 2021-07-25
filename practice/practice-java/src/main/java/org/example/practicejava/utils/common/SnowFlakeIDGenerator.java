package org.example.practicejava.utils.common;

import java.util.Optional;

/**
 * 雪花算法 64bit
 */
public class SnowFlakeIDGenerator {
    private static final String MACHINE_ID_ENV = "MACHINE_ID";
    private static final String DATACENTER_ID_ENV = "DATACENTER";
    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1528258394123L >> 3;

    /**
     * 序列号占用的位数
     */
    private final static long SEQUENCE_BIT = 9;
    /**
     * 机器标识占用的位置
     */
    private final static long MACHINE_BIT = 8L;
    /**
     * 数据中心占用的位置
     */
    private final static long DATACENTER_BIT = 0L;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ -1L << DATACENTER_BIT;
    private final static long MAX_MACHINE_NUM = -1L ^ -1L << MACHINE_BIT;
    private final static long MAX_SEQUENCE = -1L ^ -1L << SEQUENCE_BIT;

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    /**
     * 数据中心标识
     */
    private final long datacenterId;
    /**
     * 机器标识
     */
    private final long machineId;
    /**
     * 序列号
     */
    private long sequence = MAX_SEQUENCE;
    /**
     * 上一次时间戳
     */
    private long lastStmp = -1L;

    private static final SnowFlakeIDGenerator DEFAULT_GENERATOR =
        new SnowFlakeIDGenerator(Optional.ofNullable(System.getenv(DATACENTER_ID_ENV)).map(Integer::parseInt).orElse(0),
            Optional.ofNullable(System.getenv(MACHINE_ID_ENV)).map(Integer::parseInt)
                .orElse((int)(System.currentTimeMillis() % MAX_MACHINE_NUM)));

    public SnowFlakeIDGenerator(final long datacenterId, final long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    public static long nextNumber() {
        return DEFAULT_GENERATOR.nextId();
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            // 相同毫秒内，序列号自增
            sequence = sequence - 1 & MAX_SEQUENCE;
            // 同一毫秒的序列数已经达到最大
            if (sequence == MAX_SEQUENCE) {
                currStmp = getNextMill();
            }
        } else {
            // 不同毫秒内，序列号置为0
            sequence = MAX_SEQUENCE;
        }

        lastStmp = currStmp;

        // 时间戳部分 + 数据中心部分 + 机器标识部分 + 序列号部分
        return currStmp - START_STMP << TIMESTMP_LEFT | datacenterId << DATACENTER_LEFT | machineId << MACHINE_LEFT
            | sequence;

    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis() >> 3;
    }

    public static void main(String[] args) {
        System.out.println(SnowFlakeIDGenerator.nextNumber());
    }

}
