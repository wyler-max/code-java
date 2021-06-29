package org.example.practicecode.designpattern.structureType.adapter2;

public class Screen implements VGA {

    @Override
    public void send(String msg) {
        System.out.println("screen send: " + msg);
    }

    @Override
    public void accept(String msg) {
        System.out.println("screen accept: " + msg);
    }
}
