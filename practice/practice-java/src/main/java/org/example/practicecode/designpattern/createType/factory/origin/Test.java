package org.example.practicecode.designpattern.createType.factory.origin;

/**
 * @author wangyulin
 * @date 2020/6/9
 */
public class Test {
    public static void main(String[] args) {
        PizzaStore cheesePizza = new PizzaStore();
        cheesePizza.orderPizza("greek");
        cheesePizza.createPizza();
    }
}
