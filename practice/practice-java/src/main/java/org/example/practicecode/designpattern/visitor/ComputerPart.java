package org.example.practicecode.designpattern.visitor;

/**
 * 元素接口
 */
public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
