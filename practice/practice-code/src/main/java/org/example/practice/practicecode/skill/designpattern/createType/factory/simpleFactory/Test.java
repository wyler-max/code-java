package org.example.practice.practicecode.skill.designpattern.createType.factory.simpleFactory;

/**
 * @author wangyulin
 * @date 2020/6/9
 */
public class Test {
    public static void main(String[] args) {
        SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
        PizzaStore pizzaStore = new PizzaStore(simplePizzaFactory);
        pizzaStore.orderPizza("veggie");
    }
}
