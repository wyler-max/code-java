package org.example.practicecode.designpattern.memento;

/**
 * 备忘录
 */
public class Memento implements MementoIF {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
