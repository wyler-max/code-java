package org.example.practice.practicecode.javalang.utils.collection.queue.deque;

/**
 * Deque 含义是“double ended queue”，即双端队列，它既可以当作栈使用，也可以当作队列使用
 *
 *
 * Queue抛出异常/Deque 处理失败 对应Stack操作（对应的是Deque抛异常的操作） 添加头结点数据(满) add/addLast offer/offerLast(false) push 拿出头结点并且删除
 * remove/removeFirst poll/pollFirst(null) pop 拿出头结点但是不删除 element/getFirst peek/peekFirst(null) peek
 *
 * Deque 是个接口，具体要看它的实现类： ArrayDeque ConcurrentLinkedDeque LinkedBlockingDeque LinkedList
 *
 */
public class DequeTest {}
