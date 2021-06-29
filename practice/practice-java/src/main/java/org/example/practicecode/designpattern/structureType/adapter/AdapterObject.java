package org.example.practicecode.designpattern.structureType.adapter;

/**
 * 对象适配器
 */
public class AdapterObject implements Target {

    // 将待适配的类作为一个属性
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        System.out.println("pass by AdapterObject");
        adaptee.adapteeRequest();
    }
}
