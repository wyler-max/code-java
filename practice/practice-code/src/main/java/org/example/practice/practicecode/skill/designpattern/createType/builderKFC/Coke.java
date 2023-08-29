package org.example.practice.practicecode.skill.designpattern.createType.builderKFC;

/**
 * 可口可乐，继承冷饮抽象类
 */
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}
