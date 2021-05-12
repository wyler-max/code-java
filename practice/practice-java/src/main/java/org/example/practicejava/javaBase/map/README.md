
### Map 的四个常用实现类： 

HashMap/ HashTable/ LinkedHashMap/ TreeMap

#### 公共部分：

1. 默认初始容量 16，负载因子 0.75; 每次扩容为之前2倍，扩容后，元素地址改变的概率为50%
 
#### 特殊部分：

1. HashMap 最常用，线程不安全；不保持顺序；key&value 都可以接受null；不利于循环遍历
2. HashTable 线程安全的HashMap；可保持顺序；key&value 都不可为null；有利于多线程操作
3. LinkedHashMap 使用HashMap+双向链表，仅为了保持顺序；有利于循环遍历，性能优于TreeMap
4. TreeMap 线程不安全；实现了SotredMap接口，红黑树结构，元素默认按照keys的自然排序排列；不允许key=null，允许value=null；有利于循环
