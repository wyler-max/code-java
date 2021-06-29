package org.example.practicecode.designpattern.createType.factory2.simpleFactory;

public class PhoneFactory {

    public PhoneFactory() {
        System.out.println("PhoneFactory Created");
    }

    public Phone getPhone(String type) {
        switch (type) {
            case "apple":
                return new ApplePhone();
            case "huawei":
                return new HuaWeiPhone();
            default:
                return new HuaWeiPhone();
        }
    }
}
