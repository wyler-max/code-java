package org.example.practice.practiceknowbox.common.enums;

import lombok.Getter;

/**
 * @author zhangshuai
 * @date 2021/11/05
 */
@Getter
public enum ThirdUserRoleType {

    //@formatter:off
    UNKNOWN(0, "未知身份"),
    TEACHER(1, "老师"),
    GUARDIAN(2, "家长"),
    STUDENT(3, "学生");
    //@formatter:on

    /**
     * 类型
     */
    private int type;

    /**
     * 描述
     */
    private String desc;

    ThirdUserRoleType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
