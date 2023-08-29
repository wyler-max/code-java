package org.example.practice.practicecode.skill.designpattern.actionType.visitor;

/**
 * 元素接口
 */
public interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}
