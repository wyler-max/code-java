package org.example.practicecode.designpattern.createType.factory2.abstractFactory;

public class Test {
    public static void main(String[] args) {
        HuaWeiFactory huaWeiFactory = new HuaWeiFactory();
        huaWeiFactory.getPhone().make();
        huaWeiFactory.getPC().make();

        AppleFactory appleFactory = new AppleFactory();
        appleFactory.getPhone().make();
        appleFactory.getPC().make();
    }
}
