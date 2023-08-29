package org.example.practice.practicecode.skill.designpattern.actionType.stategy;

/**
 * @author wangyulin
 * @date 2020/6/1
 */
public abstract class Duck {

    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {}

    public abstract void display();

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuark() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("All Ducks can swim!");
    }

    public void setPerformFly(FlyBehavior fly) {
        this.flyBehavior = fly;
    }

    public void setPerformQuark(QuackBehavior quark) {
        this.quackBehavior = quark;
    }
}
