package org.example.practicejava.utils.collection.list.linkedlist;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * LinkedList 是一个双向链表，在内存中是不连续的（JDK1.7之前的版本是环形链表，JDK1.7之后是直线型双向链表）
 * LinkedList实现了Queue、Deque接口
 * 对内存利用率高，由于是链表，插入时较快
 *
 * 使用场景：
 * 1、增删多，读少
 * 2、不存在RandomAccess时
 * 综上，还是尽量使用ArrayList 代替LinkedList
 */
public class LinkedListTest {

    @Test
    public void testLinkedList() {
        LinkedList<String> list = new LinkedList<>();

        // add 功能
        list.add("Hello");
        list.add("World");
        list.add("Linked");
        list.add("List");
        list.add("!");
        list.add("!");
        System.out.println("LinkedList当前内容：" + list.toString());
        System.out.println("LinkedList当前容量：" + list.size());

        // set 功能
        list.set(0, "my");
        list.set(1, "name");
        list.set(4, "yulin");
        System.out.println("LinkedList当前内容：" + list.toString());
        System.out.println("LinkedList当前容量：" + list.size());

        // Iterator 循环
        System.out.println("使用Iterator循环：");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            System.out.println("使用Iterator循环：" + next);
        }

        // for 循环
        System.out.println("使用For循环：");
        for (int i=0; i<list.size(); i++) {
            System.out.println("使用For循环：" + list.get(i));
        }
        // stream 循环

        // 判断功能
        boolean isEmpty = list.isEmpty();
        boolean isContain = list.contains("my");
        // 长度功能
        list.size();
        // 集合转换成数组功能
        String[] strArray = list.toArray(new String[]{});
        System.out.println("LinkedList集合转换成数组：" + Arrays.toString(strArray));

        // 删除功能
        list.remove(0);
        list.remove("name");
        System.out.println("LinkedList当前内容：" + list.toString());
        System.out.println("LinkedList当前容量："+list.size());
        // 清空功能
        list.clear();
        System.out.println("LinkedList当前内容：" + list.toString());
        System.out.println("LinkedList当前容量："+list.size());
    }
}
