package org.example.practicecode.designpattern.facade;

/**
 * 外观模式，子系统类，CPU
 */
public class CPU {
    public void start() {
        System.out.println("CPU is start...");
    }

    public void shutDown() {
        System.out.println("CPU is shutDown...");
    }
}
