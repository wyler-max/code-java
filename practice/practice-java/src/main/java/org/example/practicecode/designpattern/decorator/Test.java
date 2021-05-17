package org.example.practicecode.designpattern.decorator;

/**
 * 装饰者测试类
 *
 * 咖啡种类：Espresso、ShortBlack、LongBlack、Decaf 调料（装饰者）：Milk、Soy、Chocolate
 */
public class Test {
    public static void main(String[] args) {

        Drink drink;
        System.out.println("order1:");
        drink = new Decaf();
        System.out.println(drink.getDescription());
        System.out.println("总计：" + drink.cost());

        System.out.println("order2:");
        drink = new LongBlack();
        drink = new Milk(drink);
        drink = new Chocolate(drink);
        drink = new Chocolate(drink);
        System.out.println(drink.getDescription());
        System.out.println("总计：" + drink.cost());
    }
}
