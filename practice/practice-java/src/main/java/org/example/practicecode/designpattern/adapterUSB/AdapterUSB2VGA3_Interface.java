package org.example.practicecode.designpattern.adapterUSB;

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

    private String adapterData(String data) {
        System.out.println("开始适配转码...");
        StringBuilder stringBuilder = new StringBuilder(data);
        return stringBuilder.reverse().toString();
    }
}
