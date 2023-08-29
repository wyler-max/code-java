package org.example.practice.practicecode.skill.designpattern.actionType.interpreter;

/**
 * 解释器模式，提供了评估语言的语法或表达式的方式。
 *
 * 这种模式实现了一个表达式接口，该接口解释一个特定的上下文。这种模式被用在 SQL 解析、符号处理引擎等。
 */
public class Test {

    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        System.out.println("John is male? " + isMale.interpret("John"));
        System.out.println("Julie is a married women? " + isMarriedWoman.interpret("Married Julie"));

        Expression expr1 = new TerminalExpression("Robert");
        Expression expr2 = new TerminalExpression("John");

        System.out.println(expr1.interpret("John") || expr2.interpret("John"));
    }

    // 规则：Robert 和 John 都是男性
    public static Expression getMaleExpression() {
        Expression expr1 = new TerminalExpression("Robert");
        Expression expr2 = new TerminalExpression("John");
        return new OrExpression(expr1, expr2);
    }

    // 规则：Julie 是一个已婚的女性
    public static Expression getMarriedWomanExpression() {
        Expression expr1 = new TerminalExpression("Julie");
        Expression expr2 = new TerminalExpression("Married");
        return new AndExpression(expr1, expr2);
    }
}
