package org.example.practicecode.designpattern.factory.factoryMethod;

/**
 * Pizza 抽象类，所有 Pizza 店都继承这个类
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
