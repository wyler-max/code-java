package org.example.practicecode.designpattern.actionType.state;

/**
 * context(player) 拥有状态的对象
 */
public class Context {

    private State state;

    public Context(){
        state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }
}
