package org.example.practicecode.designpattern.createType.builderKFC;

/**
 * 鸡肉汉堡，继承汉堡抽象类
 */
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}
