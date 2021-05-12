## Collection 的三个实现类型： 

List/ Queue/ Set

### 1 List 的常用实现类：

List 的常用实现类 ArrayList/ LinkedList

__1.1 、ArrayList__
是一个动态数组，在内存中是连续的

特性：

1. 继承了RandomAccess 接口，读取快
2. 使用尾插法+良好的扩容机制（minCapacity > 1.5? minCapacity: 1.5）使得ArrayList的插入效率不亚于LinkedList
3. remove时，最终会执行System.arraycopy()拷贝被删除之后的元素，所以效率可能不如LinkedList
4. private transient Object[] elementData; 是因为数组的  容量!=空间 ，为节省空间，不允许序列化整个数组空间，而是在序列化的时候，另外处理

共性：

1. 非线程安全的
2. 可以包含重复的元素
3. 维护了元素插入时的顺序



__1.2、LinkedList__
是一个双向链表，在内存中是不连续的（JDK1.7之前的版本是环形链表，JDK1.7之后是直线型双向链表）

1. LinkedList实现了Queue、Deque接口
2. 对内存利用率高，由于是链表，插入时较快

使用场景：

1. 增删多，读少
2. 不存在RandomAccess时
3. 综上，还是尽量使用ArrayList 代替LinkedList

ArrayList和LinkedList比较： 一般来说：ArrayList查询快!LinkedList增删快

1. 元素新增性能比较
2. 元素获取比较：


### 2 Queue 的常用实现类：

BlockingQueue/ Deque



__Queue__
用来存放 等待处理元素 的集合，这种场景一般用于缓冲、并发访问，连接池设置。

除了 extends Collection 接口的一些方法，Queue 还添加了额外的 添加、删除、查询操作。
添加、删除、查询这些个操作都提供了两种形式，其中一种在操作失败时直接抛出异常，而另一种则返回一个特殊的值:

```
Queue methods

option	Throws  exception		Returns special value
Insert		add(e)	    				offer(e)
Remove		remove()						poll()-null
Examine		element()						peek()-null
```

共性方法：

1. add(), offer() 插入值禁止为null （也不全是， LinkedList是个例外, poll(), peek() 方法在异常的时候会返回 null，添加了 null　以后，当获取时不好分辨究竟是否正确返回
2. element(), peek() 获取但不删除
3. Queue 一般都是 FIFO 的，但是也有例外，比如优先队列 priority queue（它的顺序是根据自然排序或者自定义 comparator 的）；再比如 LIFO 的队列（跟栈一样，后来进去的先出去）
4. 不论进入、出去的先后顺序是怎样的，使用 remove()，poll() 方法操作的都是 头部 的元素；而插入的位置则不一定是在队尾了，不同的 queue 会有不同的插入逻辑。



__2.1、BlockingQueue__

是个接口，extends Queue<E>

共性：

1. 线程安全
2. 不支持null值
3. 实现设计主要用于生产者消费者队列，但同时支持Collection接口

实现类：

ArrayBlockingQueue， DelayQueue， LinkedBlockingDeque， LinkedBlockingQueue， LinkedTransferQueue， PriorityBlockingQueue， SynchronousQueue



__2.1.1、LinkedBlockingQueue__

特性：

1. 线程安全
2. 不支持null值
3. 单向链表，一端进一端出，实现设计主要用于生产者消费者队列，



__2.2、Deque__
是个接口，含义是“double ended queue”，即双端队列，基于双向链表实现，它既可以当作栈使用，也可以当作队列使用

```
Deque method：
　　　　　　　　     	抛出异常   						  处理失败    					    对应Stack操作
添加头结点数据(满)   	add/addLast          offer/offerLast(false)      push
拿出头结点并且删除    remove/removeFirst   poll/pollFirst(null)      	 pop
拿出头结点但是不删除	 element/getFirst    peek/peekFirst(null)         peek
```

共性：

1. 双向链表
2. 不支持null



实现类：
ArrayDeque/ ConcurrentLinkedDeque/ LinkedBlockingDeque/ LinkedList



__2.2.1、ArrayDeque__
是Deque接口的实现类，使用了可变数组，所以没有容量上的限制

特性：

1. 线程不安全
2. 可以作为栈来使用，效率高于Stack
3. 也可以作为队列来使用，效率高于LinkedList
4. 不支持null值
5. MIN_INITIAL_CAPACITY = 8 每次扩容2倍

常用操作：
添加元素、删除元素、获取元素、队列操作、栈操作、其他操作



__2.1.2、LinkedBlockingDeque__

特性：

1. 线程安全
2. 不支持null值
3. 双向链表，两端进出（支持Collection接口），实现设计主要用于生产者消费者队列



### 3 Set 的常用实现类：

HashSet/ TreeSet



__3.1 、HashSet__
是一个无序集合

特性：

1. 线程不安全
2. 不保持插入顺序
3. 存储唯一元素，且允许为null
4. 在创建HashSet的实例时，会初始化此内部HashMap: 默认初始容量(16) 且负载因子 (0.75)


```
public HashSet() {
        map = new HashMap<>();
    }
```



```
* TreeSet 是一个有序集合
*
* TreeSet的元素支持2种排序方式：自然排序或者根据提供的Comparator进行排序。
```



__3.2 、TreeSet__
是一个有序集合

特性：

1. 线程不安全
2. 保持插入顺序，并且支持2种排序方式：自然排序（数字或字符串自然排序）或者根据提供的Comparator进行排序
3. 存储唯一元素，且允许为null
4. 在创建HashSet的实例时，会初始化此内部TreeMap（基于红黑树，没有容量限制）

PS：自然排序

- 如果是数字：比较值的大小 10 < 99 < 1000 
- 如果是字符串：先从最左边一位开始比较字符的ASCII码， 11111 < aaa < aaaaaa < hashS < hashs









