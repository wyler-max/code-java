package org.example.practice.practicecode.skill.designpattern.structureType.decorator2;

/**
 * 装饰器，继承了原对象，并重写原对象的指定方法
 */
public class Test {
    public static void main(String[] args) {

        Circle circle = new Circle();
        circle.draw();
        System.out.println("************");

        RedBoardDecorator redCircle = new RedBoardDecorator(circle);
        redCircle.draw();
        System.out.println("************");

        RedBoardDecorator redRectangle = new RedBoardDecorator(new Rectangle());
        redRectangle.draw();
    }
}
