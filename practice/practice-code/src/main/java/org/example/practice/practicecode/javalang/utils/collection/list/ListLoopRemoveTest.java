package org.example.practice.practicecode.javalang.utils.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * List 循环，并删除某个元素
 */
public class ListLoopRemoveTest {

    private List<String> list = new ArrayList<>();

    private void initData() {
        // 五虎上将
        list.add("赵云");
        list.add("黄忠");
        list.add("马超");
        list.add("关羽");
        list.add("张飞");
    }

    /**
     * List 移除某个元素
     *
     * 删除某个元素后，list的大小发生了变化，索引也发生了变化
     *
     * 方式一，使用 Iterator ，顺序向下，如果找到元素，则使用 remove 方法进行移除。 方式二，倒序遍历 List ，如果找到元素，则使用 remove 方法进行移除。 方式三，正序遍历 List
     * ，如果找到元素，则使用 remove 方法进行移除，然后进行索引 “自减”。 方式四，增强 For循环遍历 List，如果找到元素，则使用remove方法移除，然后 break 退出循环。
     * 方式五，使用jdk1.8新增的Stream流操作
     *
     * 特别注意：不能用 forEach foreach方式遍历元素的时候，是生成iterator，然后使用iterator遍历。
     * 在生成iterator的时候，会保存一个expectedModCount参数，这个是生成iterator的时候List中修改元素的次数。
     * 如果你在遍历过程中删除元素，List中modCount就会变化，如果这个modCount和exceptedModCount不一致，就会抛出异常。
     * 这个是为了安全的考虑。如果使用iterator遍历过程中，使用List修改了元素，可能会出现不正常的现象。
     * 如果使用iterator的remove方法则会正常，因为iterator的remove方法会在内部调用List的remove方法，但是会修改excepedModCount的值，因此会正常运行
     *
     */
    @Test
    public void testListLoopRemoveDemo1() {
        initData();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if ("马超".equals(str)) {
                it.remove(); // 不要用list.remove() 否则 throw ConcurrentModificationException
            }
        }
        System.out.println(list);
    }

    @Test
    public void testListLoopRemoveDemo2() {
        initData();
        // 倒叙遍历
        for (int i = list.size() - 1; i > 0; i--) {
            System.out.println("index=" + i + ";value=" + list.get(i));
            if ("马超".equals(list.get(i))) {
                System.out.println("即将删除的元素: " + list.get(i));
                list.remove(i);
            }
        }
        System.out.println(list);
    }

    @Test
    public void testListLoopRemoveDemo3() {
        initData();
        // 正序遍历
        for (int i = 0; i < list.size(); i++) {
            System.out.println("index=" + i + ";value=" + list.get(i));
            if ("马超".equals(list.get(i))) {
                System.out.println("即将删除的元素: " + list.get(i));
                list.remove(i); // 删除后后面的索引递归减一
                if (true) {
                    i--; // 调整后面的索引
                } else {
                    break; // 直接退出，不再循环
                }
            }
        }
        System.out.println(list);
    }

    @Test
    public void testListLoopRemoveDemo4() {
        initData();
        // 正序遍历
        for (String s : list) {
            System.out.println("value=" + s);
            if ("马超".equals(s)) {
                System.out.println("即将删除的元素: " + s);
                list.remove(s);
                break; // 直接退出，不再循环，否则 throw ConcurrentModificationException
            }
        }
        System.out.println(list);
    }

    @Test
    public void testListLoopRemoveDemo5() {
        initData();
        List<String> collect = list.stream().filter(s -> !"马超".equals(s)).collect(Collectors.toList());
        System.out.println(collect.toString());
    }

}
