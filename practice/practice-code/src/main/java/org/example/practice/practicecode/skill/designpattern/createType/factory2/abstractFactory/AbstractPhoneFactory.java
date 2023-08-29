package org.example.practice.practicecode.skill.designpattern.createType.factory2.abstractFactory;

import org.example.practice.practicecode.skill.designpattern.createType.factory2.factoryMethod.Phone;

public interface AbstractPhoneFactory {
    Phone getPhone();

    PC getPC();
}
