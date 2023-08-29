package org.example.practice.practicecode.javalang.utils.collection.set.hashset;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;

/**
 *
 * 1.非线程安全 2.不保持插入顺序 3.存储唯一元素并允许空值
 *
 * 在创建HashSet的实例时，会初始化此内部HashMap: 默认初始容量(16) 且负载因子 (0.75) public HashSet() { map = new HashMap<>(); }
 *
 *
 */
public class HashSetTest {

    HashSet<String> hashSet = new HashSet<>();
    HashSet<String> hashSet1 = Sets.newHashSet();

    @Before
    public void before() {
        hashSet.add("1");
        System.out.println("hashSet=" + hashSet);
    }

    @Test
    public void testHashSet() {
        hashSet.add("2");
        assertTrue(hashSet.add("3"));
        assertFalse(false);
        System.out.println("hashSet=" + hashSet);

        assertTrue(hashSet.contains("2"));
        assertFalse(hashSet.contains("4"));

        System.out.println("size=" + hashSet.size());
        System.out.println("clone=" + hashSet.clone());
        assertFalse(hashSet.isEmpty());

        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println("Iterator Value=" + iterator.next());
        }
        // Spliterator<String> spliterator = hashSet.spliterator();

        hashSet.clear();
        assertTrue(hashSet.isEmpty());
        System.out.println("hashSet=" + hashSet);
    }

    @Test
    public void testHashSet2() {
        hashSet1.add("hashSet11");
        hashSet1.add("hashSet22");
        hashSet1.add("hashSet12");

        hashSet1.add("aba");
        hashSet1.add("aaa");
        hashSet1.add("aaaaa");
        hashSet1.add("aabaa");
        hashSet1.add(null);
        hashSet1.add(null);
        System.out.println("hashSet1=" + hashSet1);
    }
}
