package org.example.practicecode.designpattern.createType.factory2.factoryMethod;

public class HuaweiPhoneFactory implements AbstractPhoneFactory {
    @Override
    public Phone getPhone() {
        return new HuaWeiPhone();
    }
}
