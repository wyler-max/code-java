package org.example.practice.practicecode.skill.designpattern.actionType.stategy;

/**
 * @author wangyulin
 * @date 2020/6/1
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I`am Flying With Wings!");
    }
}
