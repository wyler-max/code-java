package org.example.practice.practicecode.skill.designpattern.createType.factory.origin;

/**
 * @author wangyulin
 * @date 2020/6/9
 */
class PepperoniPizza extends Pizza {
    PepperoniPizza() {
        setName(this.getClass().getSimpleName());
        System.out.println("This is a " + getName() + ".");
    }
}
