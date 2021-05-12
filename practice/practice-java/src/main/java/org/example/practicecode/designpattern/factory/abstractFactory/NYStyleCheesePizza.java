package org.example.practicecode.designpattern.factory.abstractFactory;

/**
 * @author wangyulin
 * @date 2020/6/10
 */
class NYStyleCheesePizza extends Pizza {

    NYStyleCheesePizza() {
        setName(this.getClass().getSimpleName());
        System.out.println("This is a " + getName() + ".");
    }
}
