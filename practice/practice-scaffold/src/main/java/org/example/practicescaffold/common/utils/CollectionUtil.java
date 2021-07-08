package org.example.practicescaffold.common.utils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

/**
 * 集合常用的工具类
 */
public class CollectionUtil {

    /**
     * list<V> 转 map<K,V>
     */
    public static <K, V> Map<K, V> listToMap(Collection<V> list, Function<? super V, ? extends K> keyMapper) {
        Assert.notEmpty(list, "list must not empty");
        return list.stream().collect(Collectors.toMap(keyMapper, p -> p));
    }

    /**
     * list<T> 转 map<K,V>
     */
    public static <T, K, V> Map<K, V> listToMapKV(Collection<T> list, Function<? super T, ? extends K> keyMapper,
        Function<? super T, ? extends V> valueMapper) {
        Assert.notEmpty(list, "list must not empty");
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper, (k1, k2) -> k2));
    }
}
