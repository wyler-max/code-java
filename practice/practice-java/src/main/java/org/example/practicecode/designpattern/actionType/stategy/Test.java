package org.example.practicecode.designpattern.actionType.stategy;

/**
 * 策略模式： 1、将变化的部分抽象成接口，XxxBehavior，然后对其每种变化都实现，形成算法族； 2、将原抽象类中，会发生变化的方法变成其接口类型的抽象类属性，并在构造函数中实例化 3、在对象类中，利用多态重载抽象类属性；
 * 4、在抽象类中可增加setXXX，动态更新抽象类属性
 */
public class Test {
    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.display();
        mallardDuck.performFly();
        mallardDuck.performQuark();

        System.out.println("======= now i`am changed =========");
        mallardDuck.setPerformFly(new FlyNoWay());
        mallardDuck.setPerformQuark(new Squark());
        mallardDuck.performFly();
        mallardDuck.performQuark();
    }
}
