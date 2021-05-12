package org.example.practicecode.designpattern.factory.origin;

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
