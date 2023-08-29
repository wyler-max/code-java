package org.example.practice.practicecode.skill.designpattern.structureType.adapter2;

public class USBAdapter2VGA extends USBAdapter {

    private VGA vga = new Screen();

    @Override
    public void send(String msg) {
        System.out.println("AdapterVGA send: " + msg);
        dataTransform(msg);
        vga.accept(msg);
    }

    @Override
    public void accept(String msg) {
        vga.send(msg);
        dataTransform(msg);
        System.out.println("AdapterVGA accept: " + msg);
    }
}
