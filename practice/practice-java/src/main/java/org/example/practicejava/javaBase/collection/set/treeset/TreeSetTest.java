package org.example.practicejava.javaBase.collection.set.treeset;

import org.example.practicejava.javaBase.collection.common.CompareByLength;
import org.example.practicejava.javaBase.collection.common.Person;
import org.junit.Test;

import java.util.TreeSet;

/**
 * TreeSet 是一个有序集合
 *
 * TreeSet的元素支持2种排序方式：自然排序或者根据提供的Comparator进行排序。
 */
public class TreeSetTest {

    /**
     * 自然排序，怎么进去怎么出来；当然，也可以反转
     */
    @Test
    public void testTreeSet() {
        // new TreeSet<>(Comparator.comparing(o -> o.getMiniClassLessonNumber() + ";" + o.getUserNumber()))), ArrayList::new)
        TreeSet<Person> treeSet = new TreeSet<>();
        treeSet.add(new Person("赵一", 11));
        treeSet.add(new Person("钱二", 12));
        treeSet.add(new Person("孙三", 13));
        treeSet.add(new Person("李四", 14));

        System.out.println("treeSet=" + treeSet);
    }

    /**
     * 通过比较器的比较函数，对treeSet集合进行排序
     */
    @Test
    public void testTreeSet2() {
        TreeSet<String> treeSet = new TreeSet<>(new CompareByLength());
        treeSet.add("hashS");
        treeSet.add("hashs");
        treeSet.add("aaa");
        treeSet.add("aaaa");
        treeSet.add("aaaaaa");
        System.out.println("treeSet" + treeSet);

        TreeSet<String> ts = new TreeSet<>();
        ts.add("hashS");
        ts.add("hashs");
        ts.add("aaa");
        ts.add("aaaaaaa");
        ts.add("aaaaa");
        ts.add("111111111");
        ts.add("Aaaa");
        ts.add("A100");
        System.out.println("ts" + ts);

        TreeSet<Integer> ts2 = new TreeSet<>();
        ts2.add(1000);
        ts2.add(20);
        ts2.add(99);
        ts2.add(10);
        System.out.println("ts2" + ts2);
    }
}
