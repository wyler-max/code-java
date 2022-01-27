package org.example.practice.practiceknowbox.common.util;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * A generic array of xxx is created for a varargs parameter(Lists.newArrayList(E... generic))
 *
 * @author yijiu.chen
 * @date 2020/06/12
 */
public class ListsUtil {

    public static <T> List<T> buildArrayList(T item) {
        List<T> list = Lists.newArrayList();
        list.add(item);
        return list;
    }

    public static <T> List<T> buildArrayList(T item1, T item2) {
        List<T> list = Lists.newArrayList();
        list.add(item1);
        list.add(item2);
        return list;
    }

    public static <T> List<T> buildArrayList(T item1, T item2, T item3) {
        List<T> list = Lists.newArrayList();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        return list;
    }
}
