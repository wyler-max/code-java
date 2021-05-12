package org.example.practicecode.designpattern.factory.origin;

import com.example.designpattern.factory.origin.CheesePizza;
import com.example.designpattern.factory.origin.PepperoniPizza;
import com.example.designpattern.factory.origin.VeggiePizza;

/**
 * @author wangyulin
 * @date 2020/6/9
 */
public class PizzaStore {
    private Pizza pizza;

    public Pizza orderPizza(String type) {
        switch (type) {
            case "cheese":
                pizza = new CheesePizza();
                break;
            case "greek":
                pizza = new GreekPizza();
                break;
            case "pepperoni":
                pizza = new PepperoniPizza();
                break;
            case "clam":
                pizza = new ClamPizza();
                break;
            case "veggie":
                pizza = new VeggiePizza();
                break;
            default:
                pizza = new CheesePizza();
        }
        return pizza;
    }

    public Pizza createPizza() {
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
