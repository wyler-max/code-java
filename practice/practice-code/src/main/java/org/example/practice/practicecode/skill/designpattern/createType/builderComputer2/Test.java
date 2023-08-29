package org.example.practice.practicecode.skill.designpattern.createType.builderComputer2;

import org.example.practice.practicecode.skill.designpattern.createType.builderComputer2.enums.ComputerMasterEnum;
import org.example.practice.practicecode.skill.designpattern.createType.builderComputer2.enums.ComputerScreenEnum;

/**
 * 生成器模式测试2
 */
public class Test {
    public static void main(String[] args) {
        HPComputerBuilder hpComputerBuilder = new HPComputerBuilder();
        hpComputerBuilder.buildComputer();

        hpComputerBuilder.buildMaster(ComputerMasterEnum.I3.getDesc())
            .buildScreen(ComputerScreenEnum.SCREEN_1080.getDesc()).buildMouse("雷蛇鼠标");
    }
}
