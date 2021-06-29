package org.example.practicecode.designpattern.structureType.facade;

/**
 * 外观模式，客户角色
 */
public class Client {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();
        System.out.println("看了一部电影");
        computer.shutDown();
    }
}
