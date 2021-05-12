package org.example.practicecode.designpattern.factory.abstractFactory;

import com.example.designpattern.factory.abstractFactory.LDPizzaFactory;
import com.example.designpattern.factory.abstractFactory.NYPizzaFactory;

/**
 * 伦敦披萨店
 */
public class LDPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        LDPizzaFactory ldPizzaFactory = new LDPizzaFactory();
        NYPizzaFactory nyPizzaFactory = new NYPizzaFactory();
        if ("cheese".equals(type)) {
            ldPizzaFactory.createPizza(type);
        }else if ("veggie".equals(type)) {
            nyPizzaFactory.createPizza(type);
        }
        return pizza;
    }
}
