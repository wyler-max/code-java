package org.example.practicecode.designpattern.createType.builderKFC;

/**
 * 冷饮抽象类，实现商品
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
