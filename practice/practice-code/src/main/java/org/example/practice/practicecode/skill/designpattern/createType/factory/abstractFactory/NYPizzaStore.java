package org.example.practice.practicecode.skill.designpattern.createType.factory.abstractFactory;

/**
 * 纽约披萨店
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        NYPizzaFactory pizzaFactory = new NYPizzaFactory();
        return pizzaFactory.createPizza(type);
    }
}
