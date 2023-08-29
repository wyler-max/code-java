package org.example.practice.practicecode.skill.designpattern.actionType.visitor;

/**
 * 元素实体类
 */
public class Monitor implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
