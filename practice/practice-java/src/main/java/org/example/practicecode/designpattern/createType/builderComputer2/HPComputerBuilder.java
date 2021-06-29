package org.example.practicecode.designpattern.createType.builderComputer2;

import org.example.practicecode.designpattern.createType.builderComputer2.enums.ComputerMasterEnum;

/**
 * 生产惠普电脑，定义各个组件
 */
public class HPComputerBuilder extends ComputerBuilder {

    @Override
    public HPComputerBuilder buildMaster(String msg) {
        if (ComputerMasterEnum.I3.getDesc().equals(msg)) {
            System.out.println("惠普不生产该型号：" + msg);
            return this;
        }
        computer.setMaster(msg);
        System.out.println(msg);
        return this;
    }

    @Override
    public HPComputerBuilder buildScreen(String msg) {
        computer.setScreen(msg);
        System.out.println(msg);
        return this;
    }

    @Override
    public HPComputerBuilder buildKeyBoard(String msg) {
        computer.setKeyBoard(msg);
        System.out.println(msg);
        return this;
    }

    @Override
    public HPComputerBuilder buildMouse(String msg) {
        computer.setMouse(msg);
        System.out.println(msg);
        return this;
    }

    @Override
    public HPComputerBuilder buildAudio(String msg) {
        computer.setAudio(msg);
        System.out.println(msg);
        return this;
    }
}
