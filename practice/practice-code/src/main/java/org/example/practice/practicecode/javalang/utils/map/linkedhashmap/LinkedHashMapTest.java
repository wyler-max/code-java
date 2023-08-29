package org.example.practice.practicecode.javalang.utils.map.linkedhashmap;

import java.util.LinkedHashMap;

import org.junit.Test;

/**
 * LinkedHashMap映射减少了HashMap排序中的混乱，且不会像TreeMap一样导致性能损失
 *
 * 1.基于HashMap+双向链表实现 2.能保持插入的顺序 3.线程不安全
 */
public class LinkedHashMapTest {

    LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
    LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

    @Test
    public void testLinkedHashMap() {
        linkedHashMap.put(1, "val1");
        linkedHashMap.put(5, "val5");
        linkedHashMap.put(3, "val3");
        linkedHashMap.put(4, "val4");
        System.out.println("linkedHashMap=" + linkedHashMap);

        map.put("yaoming", 11);
        map.put("T-Mac", 1);
        map.put("Kobe", 24);
        map.put("Dagger", 20);
        System.out.println("map=" + map);
        map.put(null, null);
        map.put("yulin", null);
        System.out.println("map=" + map);

    }
}
