package org.example.practicecode.designpattern.actionType.visitor;

/**
 * 元素接口
 */
public interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}
