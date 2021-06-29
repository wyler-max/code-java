package org.example.practicecode.designpattern.createType.singleton;

/**
 * 4、双检锁/双重校验锁（DCL，即 double-checked locking）, 线程安全
 *
 * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能
 */
public class SingleObject4DCL {

    // 使用volatile可以禁止指令重排
    private volatile static SingleObject4DCL instance;
    private SingleObject4DCL(){};

    public static SingleObject4DCL getInstance() {
        if (instance==null) {
            synchronized (SingleObject4DCL.class) {
                if (instance==null) {
                    instance = new SingleObject4DCL();
                }
            }
        }
        return instance;
    }
}
