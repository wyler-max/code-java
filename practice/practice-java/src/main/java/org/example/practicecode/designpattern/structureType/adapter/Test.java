package org.example.practicecode.designpattern.structureType.adapter;

/**
 * client ==> Target.request ==> Adaptee.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("==== 直接实现Target ======");
        ConCreteTarget conCreteTarget = new ConCreteTarget();
        conCreteTarget.request();

        System.out.println("==== 类适配器 ======");
        Adapter adapter = new Adapter();
        adapter.request();

        System.out.println("==== 对象适配器 ======");
        AdapterObject adapterObject = new AdapterObject();
        adapterObject.request();
    }
}
