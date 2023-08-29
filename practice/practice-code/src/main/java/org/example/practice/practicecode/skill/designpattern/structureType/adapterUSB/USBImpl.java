package org.example.practice.practicecode.skill.designpattern.structureType.adapterUSB;

/**
 * USB实现类
 */
public class USBImpl implements USB {

    @Override
    public String outData(String data) {
        System.out.println("USB输出数据: " + data);
        return data;
    }
}
