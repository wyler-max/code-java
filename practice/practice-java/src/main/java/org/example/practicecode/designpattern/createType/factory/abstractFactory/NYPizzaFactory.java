package org.example.practicecode.designpattern.createType.factory.abstractFactory;

/**
 * 伦敦披萨工厂
 */
public class NYPizzaFactory implements AbstractPizzaFactory {

    @Override
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
