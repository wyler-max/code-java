package org.example.practicecode.designpattern.factory.abstractFactory;

import com.example.designpattern.factory.abstractFactory.NYStyleCheesePizza;

/**
 * 伦敦披萨工厂
 */
public class NYPizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if ("cheese".equals(type)) {
            pizza = new NYStyleCheesePizza();
        } else if ("veggie".equals(type)) {
            pizza = new NYStyleCheesePizza();
        }
        return pizza;
    }
}
