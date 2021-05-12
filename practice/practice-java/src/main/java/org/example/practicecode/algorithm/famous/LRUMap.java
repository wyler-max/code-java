package org.example.practicecode.algorithm.famous;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public class LRUMap<K, V> {

    private static final float loadFactor = 0.75f;
    private int capacity;
    private LinkedHashMap<K, V> map;

    public LRUMap(int capacity) {
        this.capacity = capacity;
        int maxCapacity = (int) Math.ceil(capacity / loadFactor) + 1;
        map = new LinkedHashMap<K, V>(maxCapacity, loadFactor, true) {
            private static final long serialVersionUID = 3801124242820219131L;
            @Override
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
                return size() > LRUMap.this.capacity;
            }
        };
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized void clear() {
        map.clear();
    }

    public synchronized int usedSize() {
        return map.size();
    }

    public static void main(String[] args) {
        LRUMap<Integer, String> lru = new LRUMap<>(6);
        System.out.println(lru.usedSize());
        System.out.println(lru.capacity);
        lru.put(1, "a");
        lru.put(2, "b");
        lru.put(3, "b");
        System.out.println(lru.usedSize());
        lru.put(4, "b");
        lru.put(5, "b");
        lru.put(6, "b");
        System.out.println(lru.usedSize());
        lru.put(7, "b");
        lru.put(8, "b");
        System.out.println(lru.usedSize());
        System.out.println(lru.map);

    }
}
