package org.example.practice.practicecode.skill.designpattern.createType.factory.abstractFactory;

/**
 * 披萨店抽象类，所有披萨店都继承这个类
 */
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    abstract Pizza createPizza(String type);
}
