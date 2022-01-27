package org.example.practice.practiceknowbox.common.enums;

import lombok.Getter;

/**
 * @author zhangshuai
 * @date 2021/1/22 下午6:31
 */
@Getter
public enum UserRoleEnum {

    // @formatter:off
    TEACHER(1, "老师"),
    STUDENT(2, "学生"),
    TEST(3, "测试用户"),
    BD(4, "BD"),
    MANAGER(5, "管理人员"),
    ;
    // @formatter:on

    /**
     * 身份
     */
    private int role;

    /**
     * 说明
     */
    private String desc;

    UserRoleEnum(int role, String desc) {
        this.role = role;
        this.desc = desc;
    }
}
