package org.example.practice.practicecode.skill.designpattern.structureType.adapterUSB;

/**
 * 对象适配器，使用对象适配
 *
 * 优点：
 * 1、使用聚合代替了继承；（合成复用原则）
 * 2、降低使用成本
 *
 * 缺点：
 * 1、要全部实现接口中的方法
 *
 * 总结：
 * 1、解决了类适配器的继承问题；
 * 2、根据合成复用原则，对象适配器优于类适配器。
 */
public class USBAdapter2VGA2_Object implements VGA {

    private USB usb = new USBImpl();

    @Override
    public String showData(String data) {
        System.out.println("===== 对象适配器 =====");
        // usb 输出
        String usbOutData = usb.outData(data);
        // 适配器工作
        String adapterOutData = adapterData(usbOutData);
        // vga 输入
        VGA vga = new VGAImpl();
        return vga.showData(adapterOutData);
    }

    private String adapterData(String data) {
        System.out.println("开始适配转码...");
        StringBuilder stringBuilder = new StringBuilder(data);
        return stringBuilder.reverse().toString();
    }
}
