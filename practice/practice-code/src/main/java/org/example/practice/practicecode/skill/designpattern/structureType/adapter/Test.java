package org.example.practice.practicecode.skill.designpattern.structureType.adapter;

/**
 * client ==> Target.request ==> Adaptee.adapteeRequest
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("==== 类适配器 ======");
        Adapter adapter = new Adapter();
        adapter.request();

        System.out.println("==== 对象适配器 ======");
        AdapterObject adapterObject = new AdapterObject();
        adapterObject.request();
    }
}
