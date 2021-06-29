package org.example.practicecode.designpattern.structureType.adapter2;

/**
 * 为 Computer 使用 USB-VGA、USB-HDMI 等适配器，去适配其他诸如 VGA、HDMI格式的显示器
 */
public class Test {
    public static void main(String[] args) {
        // test computer send & accept data
        Computer computer = new Computer();
        computer.setAdapter(new AdapterVGA());
        System.out.println("********** now send");
        computer.send("hello vga");
        System.out.println("********** now accept");
        computer.accept("world");

        computer.setAdapter(new AdapterHDMI());
        System.out.println("********** now send");
        computer.send("hello hdmi");
        System.out.println("********** now accept");
        computer.accept("world");
    }
}
