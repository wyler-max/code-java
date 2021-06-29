package org.example.practicecode.designpattern.createType.factory2.abstractFactory;

import org.example.practicecode.designpattern.createType.factory2.factoryMethod.Phone;

public interface AbstractPhoneFactory {
    Phone getPhone();

    PC getPC();
}
