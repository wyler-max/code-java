package org.example.practice.practiceknowbox.common.enums;

import lombok.Getter;

/**
 * @author zhangshuai
 * @date 2021/12/10 5:56 下午
 */
@Getter
public enum HomeworkGroupTypeEnum {
    //@formatter:off
    GENERAL(0, "一般作业"),
    SHARE(1, "共享作业"),
    ;
    //@formatter:on

    private int type;

    private String desc;

    HomeworkGroupTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
