package org.example.practicecode.designpattern.factory.factoryMethod;

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
