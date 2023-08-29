package org.example.practice.practicecode.skill.designpattern.structureType.adapter2;

public class ScreenHDMI implements HDMI {

    @Override
    public void send(String msg) {
        System.out.println("ScreenHDMI send: " + msg);
    }

    @Override
    public void accept(String msg) {
        System.out.println("ScreenHDMI accept: " + msg);
    }
}
