package org.example.practice.practiceknowbox.common.enums;

import lombok.Getter;

/**
 * 对应homework表的type字段
 *
 * @author yijiu.chen
 * @date 2020/04/24
 */
@Getter
public enum HomeworkType {

    TO_CLASS(0, "对班布置"), // NL
    TO_STUDENT(1, "对学生布置");

    private int type;
    private String desc;

    private HomeworkType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
