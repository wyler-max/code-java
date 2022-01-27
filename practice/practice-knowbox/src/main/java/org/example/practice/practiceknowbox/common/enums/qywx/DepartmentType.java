package org.example.practice.practiceknowbox.common.enums.qywx;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

/**
 * 部门管理员的类型，1表示校区负责人，2表示年级负责人，3表示班主任，4表示任课老师，5表示学段负责人
 *
 * @author zhangshuai
 * @date 2021/11/7 7:03 下午
 */
@Getter
public enum DepartmentType {

    //@formatter:off
    UNKNOWN(0, "未知"),
    SCHOOL(1, "表示校区负责人"),
    GRADE(2,"年级负责人"),
    CLASS(3, "班主任"),
    INSTRUCTOR(4, "任课老师"),
    SECTION(5, "学段负责人"),

    ;

    //@formatter:on

    private int type;

    private String desc;

    final static Map<Integer, DepartmentType> DEP_MAP;

    DepartmentType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    static {
        DEP_MAP = Arrays.stream(DepartmentType.values()).collect(Collectors.toMap(DepartmentType::getType, v -> v));
    }

    public static DepartmentType of(int type) {
        return DEP_MAP.getOrDefault(type, UNKNOWN);
    }

}
