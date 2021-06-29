package org.example.practicecode.designpattern.structureType.adapter2;

public class Computer implements USB {

    private Adapter adapter = null;

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void send(String msg) {
        System.out.println("computer send: " + msg);
        adapter.send(msg);
    }

    @Override
    public void accept(String msg) {
        adapter.accept(msg);
        System.out.println("computer accept: " + msg);
    }
}
