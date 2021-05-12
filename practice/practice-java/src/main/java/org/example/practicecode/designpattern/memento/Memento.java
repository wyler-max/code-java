package org.example.practicecode.designpattern.memento;

import com.example.designpattern.memento.MementoIF;

/**
 * 备忘录
 */
public class Memento implements MementoIF {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState(){
        return state;
    }
}
