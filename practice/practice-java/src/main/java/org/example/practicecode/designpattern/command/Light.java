package org.example.practicecode.designpattern.command;

/**
 * 实现者，灯
 */
public class Light {

    String loc = "";

    public Light(String loc) {
        this.loc = loc;
    }

    public void on() {
        System.out.println(loc + " is on.");
    }

    public void off() {
        System.out.println(loc + " is off.");
    }
}
