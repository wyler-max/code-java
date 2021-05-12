package org.example.practicecode.designpattern.adapterUSB;


/**
 * VGA实现类
 */
public class VGAImpl implements VGA {

    @Override
    public String showScreenData(String data) {
        System.out.println("VGA输出数据: " + data);
        return data;
    }
}
