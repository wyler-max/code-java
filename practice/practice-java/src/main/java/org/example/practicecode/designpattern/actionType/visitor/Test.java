package org.example.practicecode.designpattern.actionType.visitor;

/**
 * 访问者模式测试类
 */
public class Test {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
