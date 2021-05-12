package org.example.practicecode.designpattern.interpreter;

/**
 * 表达式接口
 */
public interface Expression {
    public boolean interpret(String context);
}
