package org.example.practicecode.designpattern.factory.simpleFactory;

/**
 * @author wangyulin
 * @date 2020/6/9
 */
class CheesePizza extends Pizza {
    CheesePizza() {
        // 不同的 Pizza 的订制操作
        setName(this.getClass().getSimpleName());
        System.out.println("This is a " + getName() + ".");
    }
}
