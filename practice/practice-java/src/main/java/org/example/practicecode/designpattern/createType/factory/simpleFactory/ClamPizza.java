package org.example.practicecode.designpattern.createType.factory.simpleFactory;

/**
 * @author wangyulin
 * @date 2020/6/9
 */
public class ClamPizza extends Pizza {
    public ClamPizza() {
        setName(this.getClass().getSimpleName());
        System.out.println("This is a " + getName() + ".");
    }
}
