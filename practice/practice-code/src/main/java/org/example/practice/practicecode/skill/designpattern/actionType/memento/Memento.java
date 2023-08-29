package org.example.practice.practicecode.skill.designpattern.actionType.memento;

/**
 * 备忘录
 */
public class Memento implements IMemento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
