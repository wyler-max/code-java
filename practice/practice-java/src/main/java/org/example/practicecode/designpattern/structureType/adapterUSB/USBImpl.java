package org.example.practicecode.designpattern.structureType.adapterUSB;

/**
 * USB实现类
 */
public class USBImpl implements USB {

    @Override
    public String showData(String data) {
        System.out.println("USB输出数据: " + data);
        return data;
    }
}