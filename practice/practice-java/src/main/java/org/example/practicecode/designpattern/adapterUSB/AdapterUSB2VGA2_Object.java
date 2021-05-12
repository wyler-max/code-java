package org.example.practicecode.designpattern.adapterUSB;

/**
 * 对象适配器，使用对象适配
 *
 * usb.output -> adapter -> vga.input
 */
public class AdapterUSB2VGA2_Object implements VGA{

    private USB usb = new USBImpl();

    @Override
    public String showScreenData(String data) {
        System.out.println("===== 对象适配器 =====");
        // usb 输出
        String usbOutData = usb.showData(data);
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
