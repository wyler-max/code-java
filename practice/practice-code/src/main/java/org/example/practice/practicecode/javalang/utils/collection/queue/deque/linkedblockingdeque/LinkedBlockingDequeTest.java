package org.example.practice.practicecode.javalang.utils.collection.queue.deque.linkedblockingdeque;

import java.util.concurrent.LinkedBlockingDeque;

import org.junit.Before;

/**
 * 增加了阻塞操作Blocks 和超时操作 Time-Out
 *
 * Summary of BlockingQueue methods
 *
 * Throws exception Special value Blocks Times out Insert add(e) offer(e) put(e) offer(e, time, unit) Remove remove()
 * poll() take() poll(time, unit) Examine element() peek() not applicable not applicable
 *
 * 1.线程安全 2.不支持null值 3.实现设计主要用于生产者消费者队列，但同时支持Collection接口 4.
 */
public class LinkedBlockingDequeTest {
    LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>();

    @Before
    public void before() {
        deque.add("1");
    }
}
