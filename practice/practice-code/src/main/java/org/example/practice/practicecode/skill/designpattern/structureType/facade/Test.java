package org.example.practice.practicecode.skill.designpattern.structureType.facade;

/**
 * 外观模式，客户角色
 */
public class Test {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();
        System.out.println("看了一部电影");
        computer.shutDown();
    }
}
