package org.example.practicejava.utils.delayed;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Maps;

import io.netty.util.HashedWheelTimer;
import lombok.extern.slf4j.Slf4j;

/**
 * 分层时间轮</br>
 * 为了在大的延迟时长场景下节省时间轮的空间，可以使用类似时钟时、分、秒的多级单位定义分层时间轮。 </br>
 * 从高到低，每级时间轮都记录当前和下级时间轮的触发时间，当当前时间轮触发后新增一个下级时间轮，直到最后以及时间轮触发完成。
 */
@Slf4j
public class HashedWheelTimerLayerTest {

    // 定义 秒、时、分时间轮
    private static final HashedWheelTimer t_s = new HashedWheelTimer(1, TimeUnit.SECONDS, 60);
    private static final HashedWheelTimer t_m = new HashedWheelTimer(1, TimeUnit.MINUTES, 60);
    private static final HashedWheelTimer t_h = new HashedWheelTimer(1, TimeUnit.HOURS, 24);

    private static final Map<Long, Integer> taskMap = Maps.newHashMap();

    public static void main(String[] args) throws InterruptedException {

        // todo 怎么实现
    }
}
