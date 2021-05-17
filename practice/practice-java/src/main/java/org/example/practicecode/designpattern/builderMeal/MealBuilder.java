package org.example.practicecode.designpattern.builderMeal;




public abstract class MealBuilder{
    protected ChildrenMeal meal;

    public ChildrenMeal getMeal(){return this.meal;}
    public abstract MealBuilder prepareMainItem(String msg);
    public abstract MealBuilder prepareSideItem(String msg);
    public abstract MealBuilder prepareDrink(String msg);
    public abstract MealBuilder prepareToy(String msg);
}
