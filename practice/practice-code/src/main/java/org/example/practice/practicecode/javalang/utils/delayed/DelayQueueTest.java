package org.example.practice.practicecode.javalang.utils.delayed;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue：</br>
 * 是⼀个⽀持延时获取元素的⽆界阻塞队列。</br>
 * 队列中的元素必须实现 Delayed 接⼝，并重写 getDelay(TimeUnit) 和 compareTo(Delayed) ⽅法</br>
 * 实现简单效率⾼，不引⼊第三⽅依赖。
 *
 * 但缺点也明显，内存存储，对分布式⽀持不友好，发⽣单 点故障，造成数据丢失，误解队列还存在OOM⻛险。
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        long startNano = System.nanoTime();
        // 创建订单，并设置过期时间
        OrderDelay task1 = new OrderDelay("XH00001", TimeUnit.NANOSECONDS.convert(10, TimeUnit.SECONDS) + startNano);
        OrderDelay task2 = new OrderDelay("XH00002", TimeUnit.NANOSECONDS.convert(5, TimeUnit.SECONDS) + startNano);
        OrderDelay task3 = new OrderDelay("XH00003", TimeUnit.NANOSECONDS.convert(15, TimeUnit.SECONDS) + startNano);
        // 添加到延迟队列中
        DelayQueue<OrderDelay> queue = new DelayQueue<>();
        queue.put(task1);
        queue.put(task2);
        queue.put(task3);
        // 轮询延迟队列
        while (queue.size() != 0) {
            System.out.println("After " + (System.currentTimeMillis() - start) + "ms");
            OrderDelay task = queue.peek();
            // 判断task是否过期，若过期则 doSomething 且从队列中删除
            if (task != null && task.getDelay(TimeUnit.NANOSECONDS) <= 0) {
                task.print();
                queue.poll();
            }
            Thread.sleep(1000L);
        }
    }

    static class OrderDelay implements Delayed {

        private String orderId;
        private long timeout;

        OrderDelay(String orderId, long timeout) {
            this.orderId = orderId;
            this.timeout = timeout;
        }

        /**
         * 当元素的 getDelay(TimeUnit.NANOSECONDS) <= 0时，在DelayQueue中过期
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(timeout - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (o == this) {
                return 0;
            }
            long d = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
            return d == 0 ? 0 : (d < 0 ? -1 : 1);
        }

        void print() {
            System.out.println(orderId + " 编号订单过期");
        }
    }
}
