package org.example.practicecode.designpattern.adapterUSB;

/**
 * 类适配器，使用类适配
 *
 * usb.output -> adapter -> vga.input
 */
public class AdapterUSB2VGA1_Class extends USBImpl implements VGA {

    @Override
    public String showScreenData(String data) {
        System.out.println("===== 类适配器 =====");
        // usb 输出
        String usbOutData = super.showData(data);
        // 适配器工作
        String adapterOutData = adapterData(usbOutData);
        // vga 输入
        VGA vga = new VGAImpl();
        return vga.showScreenData(adapterOutData);
    }

    private String adapterData(String data) {
        System.out.println("开始适配转码...");
        StringBuilder stringBuilder = new StringBuilder(data);
        return stringBuilder.reverse().toString();
    }
}
