package org.example.practicecode.designpattern.factory.abstractFactory;

/**
 * @author wangyulin
 * @date 2020/6/9
 */
abstract class Pizza {

    private String name;

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void prepare() {
        System.out.println("Prepare for Pizza.");
    }

    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }
    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }
    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }
}
