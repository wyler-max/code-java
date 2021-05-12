package org.example.practicecode.designpattern.singleton;

/**
 * 1、懒汉式，线程不安全（只有这里是线程不安全的，其他都是线程安全）
 *
 * 这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。
 * 因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
 * 这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作
 *
 */
public class SingleObject1LazyUnSafe {
    private static SingleObject1LazyUnSafe instance = null;
    private SingleObject1LazyUnSafe(){};

    public static SingleObject1LazyUnSafe getInstance() {
        if (instance==null) {
            return new SingleObject1LazyUnSafe();
        }
        return instance;
    }
}
