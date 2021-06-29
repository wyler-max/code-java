package org.example.practicecode.designpattern.createType.factory2.factoryMethod;

public class ApplePhoneFactory implements AbstractPhoneFactory {
    @Override
    public Phone getPhone() {
        return new ApplePhone();
    }
}
