package org.example.practice.practiceknowbox.common.enums;

import lombok.Getter;

/**
 * @author yijiu.chen
 * @date 2020/04/21
 */
@Getter
public enum UserPlatformType {
    //@formatter:off
    DING_TALK(0, "钉钉"),
    PC(1, "pc端"),
    QYWX(2, "企业微信");
    //@formatter:on

    private int type;
    private String desc;

    UserPlatformType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
