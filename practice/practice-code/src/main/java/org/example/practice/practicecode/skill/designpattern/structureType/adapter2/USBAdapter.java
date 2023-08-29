package org.example.practice.practicecode.skill.designpattern.structureType.adapter2;

public abstract class USBAdapter implements USB {
    @Override
    public abstract void send(String msg);

    @Override
    public abstract void accept(String msg);

    public String dataTransform(String msg) {
        System.out.println("dateTransform");
        return new StringBuilder().append(msg).append("-transform-tag").toString();
    }
}
