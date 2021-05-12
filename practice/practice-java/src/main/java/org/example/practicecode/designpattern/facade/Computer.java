package org.example.practicecode.designpattern.facade;

/**
 * 外观模式，门面类 Facade，Computer
 */
public class Computer {
    private CPU cpu;
    private Disk disk;
    private Memory memory;

    public Computer() {
        this.cpu = new CPU();
        this.disk = new Disk();
        this.memory = new Memory();
    }

    public void start() {
        System.out.println("Computer is start...");
        cpu.start();
        disk.start();
        memory.start();
        System.out.println("Computer finish start.");
    }

    public void shutDown() {
        System.out.println("Computer is shutDown...");
        cpu.shutDown();
        disk.shutDown();
        memory.shutDown();
        System.out.println("Computer finish shutDown.");
    }
}
