package org.example.practicecode.designpattern.actionType.command;

/**
 * 实现者，电视
 */
public class TV{

    private String brand;

    public TV(String brand) {
        this.brand = brand;
    }

    public void on() {
        System.out.println(brand + " TV is on.");
    }

    public void off() {
        System.out.println(brand + " TV is off.");
    }
}
