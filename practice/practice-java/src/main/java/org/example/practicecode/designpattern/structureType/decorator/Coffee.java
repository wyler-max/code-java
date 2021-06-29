package org.example.practicecode.designpattern.structureType.decorator;

/**
 * 被装饰对象
 */
public class Coffee extends Drink {
    @Override
    public float cost() {
        return super.getPrice();
    }
}
