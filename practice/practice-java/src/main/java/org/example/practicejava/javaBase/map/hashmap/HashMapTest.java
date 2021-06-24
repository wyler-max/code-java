package org.example.practicejava.javaBase.map.hashmap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Maps;

/**
 * HashMap 默认初始容量 16，负载因子 0.75; 每次扩容为之前2倍，扩容后，元素地址改变的概率为50%
 *
 * 1.非线程安全 2.可以接受为null的键值(key)和值(value) 3.不能保证随着时间的推移Map中的元素次序是不变的
 *
 * 如何选择合适的Map - HashMap可实现快速存储和检索，但其缺点是其包含的元素是无序的，这导致它在存在大量迭代(这里应是指循环时)的情况下表现不佳。 -
 * LinkedHashMap保留了HashMap的优势，且其包含的元素是有序的。它在有大量迭代的情况下表现更好 - TreeMap能便捷的实现对其内部元素的各种排序，但其一般性能比前两种map差。
 */
public class HashMapTest {

    HashMap<String, Integer> hashMap = new HashMap<>();
    HashMap<String, Integer> map = Maps.newHashMap();

    @Test
    public void testHashMap() {
        hashMap.put("key1", 11);
        hashMap.putIfAbsent("key2", 12);
        hashMap.put("key3", 13);
        hashMap.put("key3", 23); // 覆盖了
        hashMap.putIfAbsent("key3", 33);
        System.out.println("hashMap=" + hashMap);

        // get、getOrDefault
        System.out.println("-----get、getOrDefault ----------");
        System.out.println("getKey2=" + hashMap.get("key2"));
        Integer key1 = hashMap.getOrDefault("key1", 10);
        Integer key6 = hashMap.getOrDefault("key6", 16);
        System.out.println("key1=" + key1);
        System.out.println("key6=" + key6);

        // size、isEmpty
        System.out.println("size=" + hashMap.size());
        assertTrue(!hashMap.isEmpty());
        assertTrue(hashMap.containsKey("key1"));
        assertTrue(hashMap.containsValue(23));
        assertFalse(hashMap.containsValue(14));

        // values
        Collection<Integer> mapValues = hashMap.values();
        System.out.println("mapValues=" + mapValues);

        // keySet
        Set<String> keySet = hashMap.keySet();
        System.out.println("mapKeySet=" + keySet);

        // entrySet
        Set<Map.Entry<String, Integer>> entrySet = hashMap.entrySet();
        System.out.println("entrySet=" + entrySet);

        // replace
        System.out.println("-----replace ----------");
        boolean replace = hashMap.replace("key2", 12, 22);
        System.out.println("replace=" + replace);
        boolean replace2 = hashMap.replace("key2", 12, 32);
        System.out.println("replace2=" + replace2);
        System.out.println("hashMap=" + hashMap);

        Integer oldValue = hashMap.replace("key3", 33);
        System.out.println("oldValue=" + oldValue);
        System.out.println("hashMap=" + hashMap);

        // replaceAll
        System.out.println("-----replaceAll ----------");
        hashMap.replaceAll((s1, s2) -> {
            if (s1 == "key1") {
                s2 = 21;
            }
            return s2;
        });
        System.out.println("hashMap=" + hashMap);

        // putIfAbsent
        System.out.println("-----putIfAbsent ----------");
        hashMap.putIfAbsent("key1", 21);
        hashMap.putIfAbsent("key10", 21);
        System.out.println("hashMap=" + hashMap);

        // putAll
        System.out.println("-----putAll ----------");
        map.put("key4", 14);
        map.put("key5", 15);
        map.put("key5", null);
        map.put(null, null);
        map.put(null, null); // 覆盖了
        hashMap.putAll(map);
        System.out.println("hashMap=" + hashMap);
    }

    @Test
    public void testHashMap2() {
        hashMap.put("key1", 11);
        hashMap.put("key2", 12);
        hashMap.put("key3", 13);
        hashMap.put("key3", 23); // 覆盖了

        // forEach
        System.out.println("-----forEach ----------");
        hashMap.forEach((k, v) -> {
            System.out.println("k=" + k + "; v=" + v);
        });

        // merge
        System.out.println("-----merge ----------");
        hashMap.merge("key1", 10, (oldVal, newVal) -> oldVal + newVal);
        System.out.println("hashMap=" + hashMap);
        // merge 等价于
        /*Integer newVal = 10;
        if (hashMap.containsKey("key1")) {
            hashMap.put("key1", hashMap.get("key1") + newVal);
        } else {
            hashMap.put("key1", newVal);
        }
        System.out.println("hashMap=" + hashMap);*/

        // compute
        System.out.println("-----compute ----------");
        String k = "key2";
        hashMap.compute(k, (key, oldVal) -> oldVal + 10);
        System.out.println("hashMap=" + hashMap);
        // compute 等价于
        /*hashMap.put(k, func(k, hashMap.get(k)));
        System.out.println("hashMap=" + hashMap);*/

        // computeIfAbsent & computeIfPresent
        System.out.println("-----computeIfAbsent ----------");
        k = "key4";
        hashMap.computeIfAbsent(k, key -> 14);
        System.out.println("hashMap=" + hashMap);
        System.out.println("-----computeIfPresent ----------");
        k = "key1";
        hashMap.computeIfPresent(k, (key, oldVal) -> oldVal - 10);
        System.out.println("hashMap=" + hashMap);

        // clone
        System.out.println("-----clone ----------");
        Object clone = hashMap.clone();
        System.out.println("clone=" + clone);
        map = (HashMap<String, Integer>)hashMap.clone();
        System.out.println("map=" + map);

        // clear
        System.out.println("-----clear ----------");
        hashMap.clear();
        System.out.println("hashMap=" + hashMap);

        Map<String, Integer> stringIntegerMap = Collections.synchronizedMap(hashMap);
        System.out.println("stringIntegerMap=" + stringIntegerMap);
    }

    public Integer func(String k, Integer oldVal) {
        return oldVal + 10;
    }
}
