package org.example.practicecode.designpattern.createType.prototype.base;

import org.junit.Test;

/**
 * 使用者
 */
public class Client {
    public static void main(String[] args) {
        ConcretePrototype concretePrototype = new ConcretePrototype();
        try {
            ConcretePrototype clone = (ConcretePrototype)concretePrototype.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        int size = 10000;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            ConcretePrototype concretePrototype = new ConcretePrototype();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        int size = 10000;
        long begin = System.currentTimeMillis();
        ConcretePrototype concretePrototype = new ConcretePrototype();
        for (int i = 0; i < size; i++) {
            concretePrototype.clone();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
