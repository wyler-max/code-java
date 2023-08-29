package org.example.practice.practicecode.skill.designpattern.structureType.decorator;

/**
 * 装饰者实例，加牛奶
 */
public class Milk extends Decorator {

    public Milk(Drink drink) {
        super(drink);
        super.setDescription("Milk");
        super.setPrice(2.0F);
    }
}
