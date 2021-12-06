package org.example.practicecode.designpattern.structureType.adapter2;

/**
 * 为 Computer 使用 USB-VGA、USB-HDMI 等适配器，去适配其他诸如 VGA、HDMI格式的显示器
 */
public class Test {
    public static void main(String[] args) {
        // test computer send & accept data
        Computer computer = new Computer();
        computer.setAdapter(new USBAdapter2VGA());
        System.out.println("********** now send");
        computer.usbSend("hello vga");
        System.out.println("********** now accept");
        computer.usbAccept("world");

        computer.setAdapter(new USBAdapter2HDMI());
        System.out.println("********** now send");
        computer.usbSend("hello hdmi");
        System.out.println("********** now accept");
        computer.usbAccept("world");
    }
}
