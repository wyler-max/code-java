package org.example.practicecode.designpattern.state2;

import lombok.Getter;

import java.util.Arrays;

/**
 * 状态枚举
 */
@Getter
public enum StateEnum {
    NONE(0, "无状态"),
    NORMAL(1, "正常"),
    HIT(2, "攻击"),
    JUMP(3, "跳跃"),
    ;

    private int type;
    private String desc;

    StateEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static String of(int type) {
        return Arrays.stream(StateEnum.values()).filter(o -> o.getType() == type).map(StateEnum::getDesc).findAny().orElse(StateEnum.NONE.getDesc());
    }
}
