package org.example.practicecode.designpattern.builderComputer;

/**
 * 生产戴尔电脑，定义各个组件
 */
public class DELLComputerBuilder extends ComputerBuilder {
    @Override
    public void buildMaster() {
        computer.setMaster("i7,32g,512HD,1080");
        System.out.println("(i7,16g,512HD,1080)的惠普主机");
    }

    @Override
    public void buildScreen() {
        computer.setScreen("1366p");
        System.out.println("(1366p)的惠普显示屏");
    }

    @Override
    public void buildKeyBoard() {
        computer.setKeyBoard("Razer 青轴机械键盘");
        System.out.println("(Razer 青轴机械键盘)的键盘");
    }

    @Override
    public void buildMouse() {
        computer.setMouse("Razer 鼠标");
        System.out.println("(Razer 鼠标)的鼠标");
    }

    @Override
    public void buildAudio() {
        computer.setAudio("索尼 音响");
        System.out.println("(索尼 音响)的音响");
    }
}
