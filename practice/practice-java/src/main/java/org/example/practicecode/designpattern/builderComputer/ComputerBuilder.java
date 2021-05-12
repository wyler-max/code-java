package org.example.practicecode.designpattern.builderComputer;


/**
 * ComputerBuilder 定义构造步骤
 */
public abstract class ComputerBuilder {

    protected Computer computer;

    public Computer getComputer() {
        return computer;
    }

    public void buildComputer() {
        computer = new Computer();
        System.out.println("生产了一台电脑");
    }

    public abstract void buildMaster();
    public abstract void buildScreen();
    public abstract void buildKeyBoard();
    public abstract void buildMouse();
    public abstract void buildAudio();
}
