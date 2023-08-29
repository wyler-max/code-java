package org.example.practice.practicecode.skill.designpattern.actionType.state;

/**
 * 状态模式，类的行为是基于它的状态改变的。
 */
public class Test {
    public static void main(String[] args) {
        Context context = new Context();
        StartState startState = new StartState();
        startState.doAction(context);
        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);
        System.out.println(context.getState().toString());
    }
}
