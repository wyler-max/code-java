package org.example.practicecode.designpattern.stategy;

import com.example.designpattern.stategy.FlyRocketPowered;

/**
 * @author wangyulin
 * @date 2020/6/1
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyRocketPowered();
        quackBehavior = new Squark();
    }

    @Override
    public void display() {
        System.out.println("I`am a ModelDuck!");
    }
}
