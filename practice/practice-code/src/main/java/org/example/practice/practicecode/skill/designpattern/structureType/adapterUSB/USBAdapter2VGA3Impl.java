package org.example.practice.practicecode.skill.designpattern.structureType.adapterUSB;

/**
 * 接口适配器，使用抽象类适配
 * 如果 VGA 中有多个接口方法，仅需在适配器的抽象类 AdapterUSB2VGA3_Abstract 中处理。
 * 实际实现适配器时，可根据需要继承、重写对应的方法即可，无需全部实现。
 */
public class USBAdapter2VGA3Impl extends USBAdapter2VGA3_Abstract {

    @Override
    public String showData(String data) {
        return super.showData(data);
    }
}
