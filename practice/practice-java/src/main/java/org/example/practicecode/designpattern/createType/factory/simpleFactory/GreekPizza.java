package org.example.practicecode.designpattern.createType.factory.simpleFactory;

/**
 * @author wangyulin
 * @date 2020/6/9
 */
class GreekPizza extends Pizza {
    GreekPizza() {
        setName(this.getClass().getSimpleName());
        System.out.println("This is a " + getName() + ".");
    }
}
