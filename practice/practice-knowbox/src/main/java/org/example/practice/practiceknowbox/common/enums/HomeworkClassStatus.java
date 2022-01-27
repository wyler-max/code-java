package org.example.practice.practiceknowbox.common.enums;

import lombok.Getter;

/**
 * @author zhangshuai
 * @date 2021/11/22 3:44 下午
 */
@Getter
public enum HomeworkClassStatus {

    //@formatter:off
    NOT_VIEW(0, "未查看"),
    VIEWED(1, "已查看"),
    UN_PUBLISH(2, "未开始"),
    PUBLISHED(3, "已经开始");
    //@formatter:on

    int status;
    String desc;

    HomeworkClassStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
