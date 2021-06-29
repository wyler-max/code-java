package org.example.practicecode.designpattern.createType.factory2.factoryMethod;

public class Test {
    public static void main(String[] args) {
        HuaweiPhoneFactory huaweiPhoneFactory = new HuaweiPhoneFactory();
        huaweiPhoneFactory.getPhone().make();

        AbstractPhoneFactory applePhoneFactory = new ApplePhoneFactory();
        applePhoneFactory.getPhone().make();
    }
}
