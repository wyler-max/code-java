package org.example.practicecode.designpattern.createType.prototype.base;

/**
 * 具体的原型类，实现 Prototype （这里是 jdk 的 Cloneable） 接口
 */
public class ConcretePrototype implements Cloneable {
    public ConcretePrototype() {
        System.out.println("创建原型类");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("克隆原型类");
        return (ConcretePrototype)super.clone();
    }
}
