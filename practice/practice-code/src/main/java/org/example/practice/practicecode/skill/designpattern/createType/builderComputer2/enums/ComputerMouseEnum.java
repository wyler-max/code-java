package org.example.practice.practicecode.skill.designpattern.createType.builderComputer2.enums;

import lombok.Getter;

/**
 * 鼠标枚举
 */
@Getter
public enum ComputerMouseEnum {
    MOUSE_LOGITECH(1, "罗技"), MOUSE_RAZER(2, "雷蛇"), MOUSE_A4TECH(3, "双飞燕");

    int type;
    String desc;

    ComputerMouseEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
