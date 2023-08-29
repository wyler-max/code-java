package org.example.practice.practicecode.skill.designpattern.createType.factory2.simpleFactory;

public class Test {
    public static void main(String[] args) {
        PhoneFactory phoneFactory = new PhoneFactory();
        Phone apple = phoneFactory.getPhone("apple");
        apple.make();

        Phone huawei = phoneFactory.getPhone("huawei");
        huawei.make();
    }
}
