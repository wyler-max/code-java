package org.example.practice.practicecode.skill.designpattern.createType.factory2.factoryMethod;

public class Test {
    public static void main(String[] args) {
        HuaweiPhoneFactory huaweiPhoneFactory = new HuaweiPhoneFactory();
        huaweiPhoneFactory.getPhone().make();

        AbstractPhoneFactory applePhoneFactory = new ApplePhoneFactory();
        applePhoneFactory.getPhone().make();
    }
}
