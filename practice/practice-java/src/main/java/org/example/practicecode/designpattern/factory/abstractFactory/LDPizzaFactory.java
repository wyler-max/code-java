package org.example.practicecode.designpattern.factory.abstractFactory;

import com.example.designpattern.factory.abstractFactory.LDStyleCheesePizza;
import com.example.designpattern.factory.abstractFactory.LDStyleVeggiePizza;

/**
 * 伦敦披萨工厂
 */
public class LDPizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if ("cheese".equals(type)) {
            pizza = new LDStyleCheesePizza();
        } else if ("veggie".equals(type)) {
            pizza = new LDStyleVeggiePizza();
        }
        return pizza;
    }
}
