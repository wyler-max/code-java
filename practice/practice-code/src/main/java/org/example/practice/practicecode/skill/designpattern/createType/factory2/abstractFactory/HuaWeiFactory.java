package org.example.practice.practicecode.skill.designpattern.createType.factory2.abstractFactory;

import org.example.practice.practicecode.skill.designpattern.createType.factory2.factoryMethod.HuaWeiPhone;
import org.example.practice.practicecode.skill.designpattern.createType.factory2.factoryMethod.Phone;

public class HuaWeiFactory implements AbstractPhoneFactory {
    @Override
    public Phone getPhone() {
        return new HuaWeiPhone();
    }

    @Override
    public PC getPC() {
        return new HuaWeiPC();
    }
}
