package org.example.practicecode.designpattern.createType.builderMeal;

public class ConcreteMealBuilder extends MealBuilder {
    public ConcreteMealBuilder() {
        this.meal = new ChildrenMeal();
    }

    @Override
    public ConcreteMealBuilder prepareMainItem(String msg) {
        this.meal.setMainItem(msg);
        System.out.println(msg);
        return this;
    }

    @Override
    public ConcreteMealBuilder prepareSideItem(String msg) {
        this.meal.setSideItem(msg);
        System.out.println(msg);
        return this;
    }

    @Override
    public ConcreteMealBuilder prepareDrink(String msg) {
        this.meal.setDrink(msg);
        System.out.println(msg);
        return this;
    }

    @Override
    public ConcreteMealBuilder prepareToy(String msg) {
        this.meal.setToy(msg);
        System.out.println(msg);
        return this;
    }
}
