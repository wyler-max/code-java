package org.example.practicecode.designpattern.createType.factory.factoryMethod;

/**
 * 工厂方法模式
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
