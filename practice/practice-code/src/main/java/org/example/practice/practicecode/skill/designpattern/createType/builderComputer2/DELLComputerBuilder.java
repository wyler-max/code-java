package org.example.practice.practicecode.skill.designpattern.createType.builderComputer2;

import org.example.practice.practicecode.skill.designpattern.createType.builderComputer2.enums.ComputerMasterEnum;

/**
 * 生产戴尔电脑，定义各个组件
 */
public class DELLComputerBuilder extends ComputerBuilder {

    @Override
    public DELLComputerBuilder buildMaster(String msg) {
        if (ComputerMasterEnum.I5.getDesc().equals(msg)) {
            System.out.println("戴尔不生产该型号：" + msg);
            return this;
        }
        computer.setMaster(msg);
        System.out.println(msg);
        return this;
    }

    @Override
    public DELLComputerBuilder buildScreen(String msg) {
        computer.setScreen(msg);
        System.out.println(msg);
        return this;
    }

    @Override
    public DELLComputerBuilder buildKeyBoard(String msg) {
        computer.setKeyBoard(msg);
        System.out.println(msg);
        return this;
    }

    @Override
    public DELLComputerBuilder buildMouse(String msg) {
        computer.setMouse(msg);
        System.out.println(msg);
        return this;
    }

    @Override
    public DELLComputerBuilder buildAudio(String msg) {
        computer.setAudio(msg);
        System.out.println(msg);
        return this;
    }
}
