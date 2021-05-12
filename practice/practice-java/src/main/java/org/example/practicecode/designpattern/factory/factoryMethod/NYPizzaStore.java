package org.example.practicecode.designpattern.factory.factoryMethod;

import com.example.designpattern.factory.factoryMethod.NYStyleCheesePizza;
import com.example.designpattern.factory.factoryMethod.NYStyleVeggiePizza;

/**
 * @author wangyulin
 * @date 2020/6/10
 */
public class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza;
        switch (type) {
            case "cheese":
                pizza = new NYStyleCheesePizza();
                break;
            case "veggie":
                pizza = new NYStyleVeggiePizza();
                break;
            default:
                pizza = new NYStyleCheesePizza();
        }
        return pizza;
    }
}
