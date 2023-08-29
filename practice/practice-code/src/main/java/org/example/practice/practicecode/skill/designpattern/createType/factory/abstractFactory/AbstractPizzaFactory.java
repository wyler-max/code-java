package org.example.practice.practicecode.skill.designpattern.createType.factory.abstractFactory;

/**
 * 抽象披萨工厂
 */
public interface AbstractPizzaFactory {

    Pizza createPizza(String type);
}
