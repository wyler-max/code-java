package org.example.practicecode.designpattern.factory.factoryMethod;

import com.example.designpattern.factory.factoryMethod.ChicagoStyleCheesePizza;
import com.example.designpattern.factory.factoryMethod.ChicagoStyleVeggiePizza;

/**
 * @author wangyulin
 * @date 2020/6/10
 */
public class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza;
        switch (type) {
            case "cheese":
                pizza = new ChicagoStyleCheesePizza();
                break;
            case "veggie":
                pizza = new ChicagoStyleVeggiePizza();
                break;
            default:
                pizza = new ChicagoStyleCheesePizza();
        }
        return pizza;
    }
}
