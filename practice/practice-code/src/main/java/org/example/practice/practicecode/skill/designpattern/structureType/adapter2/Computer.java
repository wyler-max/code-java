package org.example.practice.practicecode.skill.designpattern.structureType.adapter2;

public class Computer{

    private USB usb;
    private USBAdapter adapter = null;

    public void setAdapter(USBAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * usb发送消息
     */
    public void usbSend(String msg) {
        System.out.println("computer usb send: " + msg);
        adapter.send(msg);
    }

    /**
     * usb接收消息
     */
    public void usbAccept(String msg) {
        adapter.accept(msg);
        System.out.println("computer usb accept: " + msg);
    }
}
