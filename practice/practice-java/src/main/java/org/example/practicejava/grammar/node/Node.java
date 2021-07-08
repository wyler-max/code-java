package org.example.practicejava.grammar.node;

import java.util.Map;
import java.util.Objects;

/**
 * 实现链表节点 -- Map.Entry
 */
public class Node<K, V> implements Map.Entry<K, V> {
    final int hash;
    final K key;
    V value;
    Node<K, V> next;

    Node(int hash, K key, V value, Node<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    @Override
    public final K getKey() {
        return key;
    }

    @Override
    public final V getValue() {
        return value;
    }

    @Override
    public final String toString() {
        return key + "=" + value;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    @Override
    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    @Override
    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Map.Entry) {
            Map.Entry<?, ?> e = (Map.Entry<?, ?>)o;
            if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue())) {
                return true;
            }
        }
        return false;
    }
}
