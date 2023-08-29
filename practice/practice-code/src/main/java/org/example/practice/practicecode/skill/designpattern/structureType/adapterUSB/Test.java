package org.example.practice.practicecode.skill.designpattern.structureType.adapterUSB;

/**
 * 适配器测试类：usb.output -> adapter -> vga.show
 */
public class Test {
    public static void main(String[] args) {
        String data = "dlrow olleh";

        // 类适配器
        USBAdapter2VGA1_Class adapterClass = new USBAdapter2VGA1_Class();
        adapterClass.showData(data);

        // 对象适配器
        USBAdapter2VGA2_Object adapterObject = new USBAdapter2VGA2_Object();
        adapterObject.showData(data);

        // 接口适配器
        USBAdapter2VGA3Impl adapterInterface = new USBAdapter2VGA3Impl();
        adapterInterface.showData(data);
    }
}
