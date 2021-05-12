package org.example.practicejava.javaBase.collection.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Java 集合中的 Queue 继承自 Collection 接口 ，Deque, LinkedList, PriorityQueue, BlockingQueue 等类都实现了它。
 * Queue 用来存放 等待处理元素 的集合，这种场景一般用于缓冲、并发访问，连接池设置。
 *
 * 除了 extends Collection 接口的一些方法，Queue 还添加了额外的 添加、删除、查询操作。
 * 添加、删除、查询这些个操作都提供了两种形式，其中一种在操作失败时直接抛出异常，而另一种则返回一个特殊的值:
 *
 * Summary of Queue methods
 *
 * Throws  exception	Returns special value
 * Insert	add(e)	    offer(e)
 * Remove	remove()	poll()-null
 * Examine	element()	peek()-null
 *
 * 1、add(), offer() 插入值禁止为null （也不全是， LinkedList是个例外, poll(), peek() 方法在异常的时候会返回 null，添加了 null　以后，当获取时不好分辨究竟是否正确返回
 * 2、element(), peek() 获取但不删除
 * 3、Queue 一般都是 FIFO 的，但是也有例外，比如优先队列 priority queue（它的顺序是根据自然排序或者自定义 comparator 的）；
 * 再比如 LIFO 的队列（跟栈一样，后来进去的先出去）
 * 4、不论进入、出去的先后顺序是怎样的，使用 remove()，poll() 方法操作的都是 头部 的元素；
 * 而插入的位置则不一定是在队尾了，不同的 queue 会有不同的插入逻辑。
 *
 * note：一般抛异常是因为容量限制导致
 *
 * 实现类：
 * abstract class AbstractQueue
 * ArrayBlockingQueue
 * ArrayDeque、ConcurrentLinkedDeque、LinkedBlockingDeque
 * PriorityBlockingQueue
 * etc...
 *
 */
public class QueueTest {
    // 定义一个队列
    private Queue<String> queue;
    private Queue<String> queue2;

    @Before
    public void before() {
        // 实例化队列变
        queue = new LinkedList<>();
        queue2 = new LinkedList<>();

        // add方法向队列中添加元素,返回布尔值，add方法添加失败时会抛异常,不推荐使用
        // queue.add("1");
        // queue.add("2");
        // queue.add("3");
        // queue.add("4");
        // queue.add("5");

        // offer方法向队列中添加元素，返回布尔值
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        boolean ret = queue.offer("e");
        System.out.println("ret=" + ret);
    }

    /**
     * poll 方法移除队列首个元素并返回，若队列为空，返回null
     */
    @Test
    public void testQueuePoll() {
        // 弹出元素 - 先进先出
        String poll = queue.poll();
        System.out.println("poll=" + poll);
        System.out.println("queue=" + queue);

        String poll2 = queue2.poll();
        System.out.println("poll2=" + poll2);
        System.out.println("queue2=" + queue2);
    }

    /**
     * remove 方法移除队列首个元素并返回，若队列为空，抛出异常 NoSuchElementException
     */
    @Test
    public void testQueueRemove() {
        String remove = queue.remove();
        System.out.println("remove=" + remove);
        System.out.println("queue=" + queue);

        // Throw NoSuchElementException
        String remove2 = queue2.remove();
        System.out.println("remove2=" + remove2);
        System.out.println("queue2=" + queue2);
    }

    @Test
    public void testQueuePeek() {
        String peek = queue.peek();
        System.out.println("peek=" + peek);
        System.out.println("queue=" + queue);

        String peek2 = queue2.peek();
        System.out.println("peek2=" + peek2);
        System.out.println("queue2=" + queue2);
    }

    @Test
    public void testQueueElement() {
        String element = queue.element();
        System.out.println("element=" + element);
        System.out.println("queue=" + queue);

        // Throw NoSuchElementException
        String element2 = queue2.element();
        System.out.println("element2=" + element2);
        System.out.println("queue2=" + queue2);
    }

}
