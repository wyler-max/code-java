package org.example.practicecode.designpattern.visitor;

import com.example.designpattern.visitor.Computer;
import com.example.designpattern.visitor.ComputerPart;
import com.example.designpattern.visitor.ComputerPartDisplayVisitor;

/**
 * 访问者模式测试类
 */
public class Test {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
