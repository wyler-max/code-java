package org.example.springbootstart.services.enums;

import lombok.Getter;

/**
 *
 */
@Getter
public enum ActivityTypeEnum {
    TYPEA(1, "TypeA"),TYPEB(2, "TypeB"),TYPEC(3, "TypeC");

    int type;
    String desc;

    ActivityTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
