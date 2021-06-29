package org.example.practicecode.designpattern.structureType.adapterUSB;

/**
 * 接口适配器，使用抽象类适配
 *
 * usb.output -> adapter -> vga.input
 */
public class AdapterUSB2VGA3_Interface extends AdapterUSB2VGA3_Abstract {

    @Override
    public String showScreenData(String data) {
        return super.showScreenData(data);
    }
}
