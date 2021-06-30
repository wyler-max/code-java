package org.example.practicecode.designpattern.structureType.flyweight;

/**
 * 享元模式，使用 HashMap 避免对象的重复创建。在spring中大量使用该模式
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
