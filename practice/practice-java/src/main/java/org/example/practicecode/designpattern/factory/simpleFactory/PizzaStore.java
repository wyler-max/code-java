package org.example.practicecode.designpattern.factory.simpleFactory;

import com.example.designpattern.factory.simpleFactory.SimplePizzaFactory;

import java.text.SimpleDateFormat;

/**
 * @author wangyulin
 * @date 2020/6/9
 */
public class PizzaStore {
    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = factory.createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
