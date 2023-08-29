package org.example.practice.practicecode.skill.designpattern.actionType.interpreter;

/**
 * 表达式接口
 */
public interface Expression {
    boolean interpret(String context);
}
