package org.example.practice.practicecode.skill.designpattern.createType.factory.abstractFactory;

/**
 * @author wangyulin
 * @date 2020/6/10
 */
class LDStyleVeggiePizza extends Pizza {

    LDStyleVeggiePizza() {
        setName(this.getClass().getSimpleName());
        System.out.println("This is a " + getName() + ".");
    }
}
