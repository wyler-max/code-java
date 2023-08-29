package org.example.practice.practicecode.skill.designpattern.createType.builderComputer2.enums;

import lombok.Getter;

/**
 * 屏幕枚举
 */
@Getter
public enum ComputerScreenEnum {
    SCREEN_1080(1, "1080p"), SCREEN_1366(2, "1366p"), SCREEN_1688(3, "1688p");

    int type;
    String desc;

    ComputerScreenEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
