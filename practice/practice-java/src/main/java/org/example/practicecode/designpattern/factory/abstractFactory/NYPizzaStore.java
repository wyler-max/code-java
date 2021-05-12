package org.example.practicecode.designpattern.factory.abstractFactory;

import com.example.designpattern.factory.abstractFactory.NYPizzaFactory;

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
