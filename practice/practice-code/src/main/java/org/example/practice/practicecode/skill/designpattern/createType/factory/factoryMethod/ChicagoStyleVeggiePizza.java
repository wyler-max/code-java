package org.example.practice.practicecode.skill.designpattern.createType.factory.factoryMethod;

/**
 * @author wangyulin
 * @date 2020/6/10
 */
public class ChicagoStyleVeggiePizza extends Pizza {

    public ChicagoStyleVeggiePizza() {
        setName(this.getClass().getSimpleName());
        System.out.println("This is a " + getName() + ".");
    }
}
