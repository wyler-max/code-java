package org.example.practicecode.designpattern.createType.builderKFC;

/**
 * 食品元素接口，具象：汉堡、冷饮
 */
public interface Item {
    public String name();

    public Packing packing();

    public float price();
}
