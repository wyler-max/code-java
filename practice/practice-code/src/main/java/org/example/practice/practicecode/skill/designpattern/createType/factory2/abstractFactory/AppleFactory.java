package org.example.practice.practicecode.skill.designpattern.createType.factory2.abstractFactory;

import org.example.practice.practicecode.skill.designpattern.createType.factory2.factoryMethod.ApplePhone;
import org.example.practice.practicecode.skill.designpattern.createType.factory2.factoryMethod.Phone;

public class AppleFactory implements AbstractPhoneFactory {
    @Override
    public Phone getPhone() {
        return new ApplePhone();
    }

    @Override
    public PC getPC() {
        return new ApplePC();
    }
}
