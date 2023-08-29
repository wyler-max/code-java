package org.example.practice.practicecode.javalang.utils.delayed;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ⽆限循环实现延迟任务。
 *
 * 缺点：会一直占用CPU资源，浪费
 */
public class SimpleDelayTaskTest {

    private static Map<String, Long> taskMap = new HashMap<>();

    public static void lookTask() {
        Long itemLong = 0L;
        while (true) {
            Iterator iterator = taskMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry)iterator.next();
                itemLong = (Long)entry.getValue();
                if (Instant.now().toEpochMilli() >= itemLong) {
                    System.out.println("执行任务：" + entry.getKey() + ", 执行时间：" + new Date());
                    iterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("程序启动");
        taskMap.put("task-1", Instant.now().plusSeconds(10).toEpochMilli());
        taskMap.put("task-2", Instant.now().plusSeconds(5).toEpochMilli());
        taskMap.put("task-3", Instant.now().plusSeconds(15).toEpochMilli());
        lookTask();
    }
}
