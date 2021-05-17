package org.example.practicecode.designpattern.builderComputer;

/**
 * 对组件进行组装并生成产品
 */
public class Director {
    private ComputerBuilder computerBuilder;

    public void setComputerBuilder(ComputerBuilder computerBuilder) {
        this.computerBuilder = computerBuilder;
    }

    public Computer getComputer() {
        return computerBuilder.getComputer();
    }

    public void constructComputer() {
        computerBuilder.buildComputer();
        computerBuilder.buildMaster();
        computerBuilder.buildScreen();
        computerBuilder.buildKeyBoard();
        computerBuilder.buildMouse();
        computerBuilder.buildAudio();
    }
}
