package org.example.practicecode.designpattern.createType.factory.simpleFactory;

/**
 * @author wangyulin
 * @date 2020/6/9
 */
class VeggiePizza extends Pizza {
    VeggiePizza() {
        setName(this.getClass().getSimpleName());
        System.out.println("This is a " + getName() + ".");
    }

    @Override
    void bake() {
        System.out.println("VeggiePizza bake soft.");
    }
}
