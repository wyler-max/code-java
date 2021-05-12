package org.example.practicecode.designpattern.factory.abstractFactory;

/**
 * 抽象披萨工厂
 */
public interface AbstractPizzaFactory {

    Pizza createPizza(String type);
}
