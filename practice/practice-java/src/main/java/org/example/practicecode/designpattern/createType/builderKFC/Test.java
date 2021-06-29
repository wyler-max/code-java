package org.example.practicecode.designpattern.createType.builderKFC;

/**
 * 生成器模式测试
 */
public class Test {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        System.out.println("套餐：vegMeal");
        Meal vegMeal = mealBuilder.prepareVegMeal();
        vegMeal.showItems();
        System.out.println("Total Cost：" + vegMeal.getCost());

        System.out.println("套餐：nonMeal");
        Meal nonMeal = mealBuilder.prepareNonVegMeal();
        nonMeal.showItems();
        System.out.println("Total Cost：" + nonMeal.getCost());
    }
}
