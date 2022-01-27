package org.example.practice.practiceknowbox.common.enums;

import lombok.Getter;

/**
 * @author zhangshuai
 * @date 2021/12/7 6:02 下午
 */
@Getter
public enum ShareHomeworkType {

    //@formatter:off
    SAME_SCHOOL(0, "同校布置的"),
    POPULAR(1, "热门"),
    LATEST(2, "最新"),
    SAME_SCHOOL_USED(3, "同校使用过的"),
    //@formatter:on

    ;

    private int type;

    private String desc;

    ShareHomeworkType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
