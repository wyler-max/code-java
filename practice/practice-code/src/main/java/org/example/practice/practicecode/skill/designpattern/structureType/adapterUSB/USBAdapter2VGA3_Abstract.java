package org.example.practice.practicecode.skill.designpattern.structureType.adapterUSB;

/**
 * 接口适配器，使用抽象类实现接口，并为该接口中每个方法提供一个默认实现
 *
 * 优点：
 * 1、是对象适配器的进一步优化，避免在直接使用过程中实现所有的接口（不想实现的可以覆写为抽象方法）
 *
 * 缺点：
 * 1、代码复杂度更高一些
 *
 * 总结：
 * 1、对象适配器和接口适配器是同一种模式的两种写法；实际使用时，根据需要选型。
 */
public abstract class USBAdapter2VGA3_Abstract implements VGA {

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

    @Override
    public void otherA() {
        // override this method
    }

    @Override
    public void otherB() {
        // override this method
    }

    private String adapterData(String data) {
        System.out.println("开始适配转码...");
        StringBuilder stringBuilder = new StringBuilder(data);
        return stringBuilder.reverse().toString();
    }
}
