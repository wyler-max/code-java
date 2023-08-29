package org.example.practice.practicecode.skill.designpattern.createType.builderComputer2.enums;

import lombok.Getter;

/**
 * 主机枚举
 */
@Getter
public enum ComputerMasterEnum {
    I3(1, "i3cpu"), I5(2, "i5cpu"), I7(3, "i7cpu");

    int type;
    String desc;

    ComputerMasterEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
