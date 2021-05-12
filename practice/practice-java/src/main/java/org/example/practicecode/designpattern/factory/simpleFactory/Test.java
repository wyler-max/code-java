package org.example.practicecode.designpattern.factory.simpleFactory;

import com.example.designpattern.factory.simpleFactory.PizzaStore;
import com.example.designpattern.factory.simpleFactory.SimplePizzaFactory;

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
