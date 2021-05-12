package org.example.practicecode.designpattern.state;

import com.example.designpattern.state.Context;
import com.example.designpattern.state.StartState;
import com.example.designpattern.state.StopState;

/**
 *
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
