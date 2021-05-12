package org.example.practicecode.designpattern.singleton;

/**
 * 单例模式（一般情况：3、饿汉式）
 *
 * 使用习惯：
 * 1、一般情况下，不建议使用第 1 种和第 2 种懒汉方式，建议使用第 3 种饿汉方式。
 * 2、只有在要明确实现 lazy loading 效果时，才会使用第 5 种登记方式。
 * 3、如果涉及到反序列化创建对象时，可以尝试使用第 6 种枚举方式。
 * 4、如果有其他特殊的需求，可以考虑使用第 4 种双检锁方式。
 */
public class SingleObject {
    private static SingleObject instance = new SingleObject();

    private SingleObject() {
    }

    public static SingleObject getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello world!");
    }
}
