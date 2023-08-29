package org.example.practice.practicecode.skill.designpattern.actionType.stategy;

/**
 * @author wangyulin
 * @date 2020/6/1
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack!");
    }
}
