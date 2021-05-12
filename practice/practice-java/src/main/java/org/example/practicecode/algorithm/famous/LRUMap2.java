package org.example.practicecode.algorithm.famous;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUMap2<K, V> extends LinkedHashMap<K, V> {
    private static final float loadFactor = 0.75F;
    private int capacity;

    LRUMap2(int capacity) {
        super(capacity, loadFactor, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > this.capacity;
    }

    @Override
    public V get(Object key) {
        return super.get(key);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }

    public int usedSize() {
        return size();
    }

    public Collection<Map.Entry<K,V>> getAll() {
        return new ArrayList<>(super.entrySet());
    }

    public static void main(String[] args) {
        LRUMap2<Integer, String> map2 = new LRUMap2<>(4);
        map2.put(1, "a");
        map2.put(2, "a");
        map2.put(3, "a");
        map2.put(4, "a");
        map2.put(5, "a");
        map2.put(6, "a");
        System.out.println(map2.usedSize());
        System.out.println(map2.getAll());
    }
}
