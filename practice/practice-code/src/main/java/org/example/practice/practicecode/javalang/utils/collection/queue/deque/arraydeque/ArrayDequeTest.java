package org.example.practice.practicecode.javalang.utils.collection.queue.deque.arraydeque;

import java.util.ArrayDeque;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * ArrayDeque是Deque接口的一个实现，使用了可变数组，所以没有容量上的限制 1.线程不安全 2.可以作为栈来使用，效率高于Stack 3.也可以作为队列来使用，效率高于LinkedList 4.不支持null值
 * 5.MIN_INITIAL_CAPACITY = 8 每次扩容2倍
 *
 * 常用操作： 添加元素、删除元素、获取元素、队列操作、栈操作、其他操作
 *
 */
public class ArrayDequeTest {

    ArrayDeque<String> arrayDeque = new ArrayDeque<>();

    @Before
    public void before() {
        arrayDeque.add("add-1");
        arrayDeque.add("add-2");

        arrayDeque.offer("offer-1");
        arrayDeque.offer("offer-2");
        arrayDeque.offer("offer-3");

        System.out.println("arrayDeque=" + arrayDeque);
    }

    /**
     * 添加元素 addFirst(E e)在数组前面添加元素 addLast(E e)在数组后面添加元素 offerFirst(E e) 在数组前面添加元素，并返回是否添加成功 offerLast(E e)
     * 在数组后天添加元素，并返回是否添加成功
     */
    @Test
    public void testArrayDequeAdd() {

        arrayDeque.addFirst("ad-f 1");
        arrayDeque.addFirst("ad-f 2");

        arrayDeque.addLast("ad-l 1");
        arrayDeque.addLast("ad-l 2");
        System.out.println("add添加后 arrayDeque=" + arrayDeque);

        arrayDeque.offerFirst("of-f 1");
        arrayDeque.offerFirst("of-f 2");

        arrayDeque.offerLast("of-l 1");
        boolean b = arrayDeque.offerLast("of-l 2");
        System.out.println("b=" + b);
        System.out.println("offer添加后 arrayDeque=" + arrayDeque);
    }

    /**
     * 删除元素 removeFirst()删除第一个元素，并返回删除元素的值,如果元素为null，将抛出异常 pollFirst()删除第一个元素，并返回删除元素的值，如果元素为null，将返回null
     * removeLast()删除最后一个元素，并返回删除元素的值，如果为null，将抛出异常 pollLast()删除最后一个元素，并返回删除元素的值，如果为null，将返回null
     * removeFirstOccurrence(Object o) 删除第一次出现的指定元素 removeLastOccurrence(Object o) 删除最后一次出现的指定元素
     */
    @Test
    public void testArrayDequeDel() {
        // testArrayDequeAdd();

        String s2 = arrayDeque.removeFirst();
        System.out.println("s2=" + s2);
        System.out.println("删除后 arrayDeque=" + arrayDeque);

        String s3 = arrayDeque.removeLast();
        System.out.println("s3=" + s3);
        System.out.println("删除后 arrayDeque=" + arrayDeque);

        String s4 = arrayDeque.pollFirst();
        System.out.println("s4=" + s4);
        System.out.println("删除后 arrayDeque=" + arrayDeque);
        String s5 = arrayDeque.pollLast();
        System.out.println("s5=" + s5);
        System.out.println("删除后 arrayDeque=" + arrayDeque);

        // 删除第一次 or 最后一次出现的指定元素
        boolean b1 = arrayDeque.removeFirstOccurrence("add-2");
        System.out.println("b1=" + b1);
        System.out.println("删除后 arrayDeque=" + arrayDeque);
        boolean b2 = arrayDeque.removeLastOccurrence("offer");
        System.out.println("b2=" + b2);
        System.out.println("删除后 arrayDeque=" + arrayDeque);
    }

    /**
     * 获取元素 getFirst() 获取第一个元素,如果没有将抛出异常 getLast() 获取最后一个元素，如果没有将抛出异常
     */
    @Test
    public void testArrayDequeGet() {
        String first = arrayDeque.getFirst();
        String last = arrayDeque.getLast();
        System.out.println("first=" + first);
        System.out.println("last=" + last);
        System.out.println("arrayDeque=" + arrayDeque);
    }

    /**
     * 队列操作 add(E e) 在队列尾部添加一个元素 offer(E e) 在队列尾部添加一个元素，并返回是否成功 remove()
     * 删除队列中第一个元素，并返回该元素的值，如果元素为null，将抛出异常(其实底层调用的是removeFirst()) poll()
     * 删除队列中第一个元素，并返回该元素的值,如果元素为null，将返回null(其实调用的是pollFirst()) element() 获取第一个元素，如果没有将抛出异常 peek() 获取第一个元素，如果返回null
     */
    @Test
    public void testArrayDequeQueue() {
        arrayDeque.add("queue-add");
        arrayDeque.offer("queue-offer");
        System.out.println("arrayDeque=" + arrayDeque);
        String remove = arrayDeque.remove();
        String poll = arrayDeque.poll();
        String element = arrayDeque.element();
        String peek = arrayDeque.peek();
        System.out.println("remove=" + remove);
        System.out.println("poll=" + poll);
        System.out.println("element=" + element);
        System.out.println("peek=" + peek);
        System.out.println("arrayDeque=" + arrayDeque);
    }

    /**
     * 栈操作 push(E e) 栈顶添加一个元素 pop(E e) 移除栈顶元素,如果栈顶没有元素将抛出异常
     */
    @Test
    public void testArrayDequeStack() {
        arrayDeque.push("push");
        System.out.println("arrayDeque=" + arrayDeque);
        String pop = arrayDeque.pop();
        System.out.println("pop=" + pop);
        System.out.println("arrayDeque=" + arrayDeque);
    }

    /**
     * 其他 size() 获取队列中元素个数 isEmpty() 判断队列是否为空 iterator() 迭代器，从前向后迭代 descendingIterator() 迭代器，从后向前迭代 contain(Object o)
     * 判断队列中是否存在该元素 toArray() 转成数组 clear() 清空队列 clone() 克隆(复制)一个新的队列
     */
    @Test
    public void testArrayDequeOther() {
        System.out.println("size=" + arrayDeque.size());
        System.out.println("isEmpty=" + arrayDeque.isEmpty());
        // iterator
        // descendingIterator
        Iterator<String> iterator = arrayDeque.iterator();
        while (iterator.hasNext()) {
            System.out.println("Iterator value: " + iterator.next());
        }
        Iterator<String> stringIterator = arrayDeque.descendingIterator();
        while (stringIterator.hasNext()) {
            System.out.println("descendingIterator value: " + stringIterator.next());
        }
        System.out.println("isContains=" + arrayDeque.contains("add-2"));
        System.out.println("isContains=" + arrayDeque.contains("add2"));
        System.out.println("toArray=" + arrayDeque.toArray().getClass());
        System.out.println("hashCodeOrigin=" + arrayDeque.hashCode());
        ArrayDeque<String> clone = arrayDeque.clone();
        System.out.println("hashCodeOrigin=" + clone.hashCode());
        System.out.println("clone=" + clone);
        arrayDeque.clear();
        System.out.println("arrayDeque=" + arrayDeque);
    }
}
