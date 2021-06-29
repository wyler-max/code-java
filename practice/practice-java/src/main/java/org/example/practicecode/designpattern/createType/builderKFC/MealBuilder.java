package org.example.practicecode.designpattern.createType.builderKFC;

/**
 * 实际创建Meal对象
 */
public class MealBuilder {

    // 套餐1
    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger()); // 蔬菜汉堡
        meal.addItem(new Coke()); // 可口可乐
        return meal;
    }

    // 套餐2
    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger()); // 鸡肉汉堡
        meal.addItem(new Pepsi()); // 百事可乐
        return meal;
    }
}
