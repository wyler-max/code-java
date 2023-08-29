package org.example.practice.practicecode.skill.designpattern.structureType.adapterUSB;

/**
 * 类适配器，使用类适配
 *
 * 优点：
 * 1、继承了被适配的类，可以重写该类中的方法，提高了适配器的灵活性。
 *
 * 缺点：
 * 1、Java是单继承，适配器继承了被适配的类，所以目标类只能是接口；
 * 2、被适配的类的方法，在适配器中会暴露出来（还是继承的问题），增加使用成本；
 * 3、要全部实现接口中的方法
 *
 * 总结：成也继承，败也继承。
 */
public class USBAdapter2VGA1_Class extends USBImpl implements VGA {

    @Override
    public String showData(String data) {
        System.out.println("===== 类适配器 =====");
        // usb 输出
        String usbOutData = super.outData(data);
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
