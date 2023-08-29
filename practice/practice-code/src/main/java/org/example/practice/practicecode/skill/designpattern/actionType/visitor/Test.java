package org.example.practice.practicecode.skill.designpattern.actionType.visitor;

/**
 * 访问者模式，
 */
public class Test {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
