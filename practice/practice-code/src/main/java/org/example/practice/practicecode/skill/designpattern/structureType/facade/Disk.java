package org.example.practice.practicecode.skill.designpattern.structureType.facade;

/**
 * 外观模式，子系统类，Disk
 */
public class Disk {
    public void start() {
        System.out.println("Disk is start...");
    }

    public void shutDown() {
        System.out.println("Disk is shutDown...");
    }
}
