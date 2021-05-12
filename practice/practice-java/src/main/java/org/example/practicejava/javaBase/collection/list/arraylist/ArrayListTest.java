package org.example.practicejava.javaBase.collection.list.arraylist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList 是一个动态数组，在内存中是连续的
 *
 * 1、
 * 2、继承了RandomAccess 接口，读取快
 * 3、使用尾插法+良好的扩容机制（minCapacity > 1.5? minCapacity: 1.5）使得ArrayList的插入效率不亚于LinkedList
 * 4、remove时，最终会执行System.arraycopy()拷贝被删除之后的元素，所以效率可能不如LinkedList
 * 5、private transient Object[] elementData; 是因为数组的  容量!=空间 ，为节省空间，不允许序列化整个数组空间，而是在序列化的时候，另外处理
 *
 * 共性：
 * 1、非线程安全的
 * 2、可以包含重复的元素
 * 3、维护了元素插入时的顺序
 */
public class ArrayListTest {

    @Test
    public void testArrayList() {
        //创建ArrayList集合：
        List<String> list = new ArrayList<String>();
        System.out.println("ArrayList集合初始化容量："+list.size());

        //添加功能：
        list.add("Hello");
        list.add("world");
        list.add(2,"!");
        System.out.println("ArrayList当前内容："+list.toString());
        System.out.println("ArrayList当前容量："+list.size());

        //修改功能：
        list.set(0,"my");
        list.set(1,"name");
        System.out.println("ArrayList当前内容："+list.toString());
        System.out.println("ArrayList当前容量："+list.size());

        //获取功能：
        String element = list.get(0);
        System.out.println(element);

        //迭代器遍历集合：(ArrayList实际的跌倒器是Itr对象)
        System.out.println("Iterator 输出值：");
        Iterator<String> iterator =  list.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println("Iterator 输出值：" + next);
        }

        //for循环迭代集合：
        System.out.println("for循环 输出值：");
        for(String str:list){
            System.out.println("for循环 输出值：" + str);
        }

        //判断功能：
        boolean isEmpty = list.isEmpty();
        boolean isContain = list.contains("my");

        //长度功能：
        int size = list.size();

        //把集合转换成数组：
        String[] strArray = list.toArray(new String[]{});

        //删除功能：
        System.out.println("删除元素：");
        list.remove(0);
        list.remove("world");
        System.out.println("ArrayList当前容量："+list.size());

        //清空功能：
        System.out.println("清空元素：");
        list.clear();
        System.out.println("ArrayList当前容量："+list.size());
    }
}

