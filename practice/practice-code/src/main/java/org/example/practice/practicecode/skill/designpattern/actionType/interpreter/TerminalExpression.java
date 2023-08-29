package org.example.practice.practicecode.skill.designpattern.actionType.interpreter;

/**
 * 主要解释器
 */
public class TerminalExpression implements Expression {
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(data);
    }
}
