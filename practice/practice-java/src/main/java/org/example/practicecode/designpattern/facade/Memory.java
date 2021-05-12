package org.example.practicecode.designpattern.facade;

/**
 * 外观模式，子系统类，Memory
 */
public class Memory {
    public void start() {
        System.out.println("Memory is start...");
    }

    public void shutDown() {
        System.out.println("Memory is shutDown...");
    }
}
