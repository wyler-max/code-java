package org.example.practicecode.designpattern.actionType.visitor;

/**
 * 访问者接口
 */
public interface ComputerPartVisitor {
    void visit(Computer computer);

    void visit(Mouse mouse);

    void visit(Keyboard keyboard);

    void visit(Monitor monitor);
}
