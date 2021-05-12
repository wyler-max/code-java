package org.example.practicecode.designpattern.flyweight;

import com.example.designpattern.flyweight.FlyweightFactory;
import com.example.designpattern.flyweight.IFlyweight;

/**
 * 享元模式测试类
 */
public class Test {
    public static void main(String[] args) {

        FlyweightFactory flyweightFactory = new FlyweightFactory();
        IFlyweight flyweight1 = flyweightFactory.getFlyweight("aaa");
        IFlyweight flyweight2 = flyweightFactory.getFlyweight("bbb");
        IFlyweight flyweight3 = flyweightFactory.getFlyweight("aaa");

        flyweight1.print();
        flyweight2.print();
        flyweight3.print();

        System.out.println(flyweightFactory.getFlyweightMapSize());
    }
}
