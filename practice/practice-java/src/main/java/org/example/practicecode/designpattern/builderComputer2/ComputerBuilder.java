package org.example.practicecode.designpattern.builderComputer2;


import javafx.util.Builder;

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

    public abstract ComputerBuilder buildMaster(String msg);
    public abstract ComputerBuilder buildScreen(String msg);
    public abstract ComputerBuilder buildKeyBoard(String msg);
    public abstract ComputerBuilder buildMouse(String msg);
    public abstract ComputerBuilder buildAudio(String msg);
}
