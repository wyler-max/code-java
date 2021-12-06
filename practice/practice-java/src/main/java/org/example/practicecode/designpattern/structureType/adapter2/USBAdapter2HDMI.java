package org.example.practicecode.designpattern.structureType.adapter2;

public class USBAdapter2HDMI extends USBAdapter {

    private VGA vga = new Screen();

    @Override
    public void send(String msg) {
        System.out.println("AdapterHDMI send: " + msg);
        dataTransform(msg);
        vga.accept(msg);
    }

    @Override
    public void accept(String msg) {
        vga.send(msg);
        dataTransform(msg);
        System.out.println("AdapterHDMI accept: " + msg);
    }
}
