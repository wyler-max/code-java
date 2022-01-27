package org.example.practice.practiceknowbox.common.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

/**
 * @author zhangshuai
 * @date 2021/1/12 下午4:07
 */
@Getter
public enum GradeEnum {

    //@formatter:off
    UNKNOWN("未知", -1),
    KINDERGARTEN_ZERO("非标准年级",0),
    KINDERGARTEN_SMALL_S("幼儿园小小班",1),
    KINDERGARTEN_SMALL(")幼儿园小班",2),
    KINDERGARTEN_MIDDLE  ("幼儿园中班",3),
    KINDERGARTEN("幼儿园大班",4),
    KINDERGARTEN_PRE_SCHOOL("幼儿园学前班",5),
    FIRST("小学一年级",31),
    SECOND("小学二年级",32),
    THIRD("小学三年级",33),
    FORTH("小学四年级",34),
    FIFTH("小学五年级",35),
    SIXTH("小学六年级",36),
    SEVENTH("小学七年级",37),
    EIGHT("小学八年级",38),
    NINTH("小学九年级",39),
    JUNIOR_FIRST("初中一年级",61),
    JUNIOR_SECOND("初中二年级",62),
    JUNIOR_THIRD("初中三年级",63),
    JUNIOR_FORTH("初中四年级",64),
    HIGH_FIRST("高中一年级",91),
    HIGH_SECOND("高中二年级",92),
    HIGH_THIRD("高中三年级",93),
    HIGH_FORTH("高中四年级",94),
    CUSTOM_FIRST("自定义学段一年级",121),
    CUSTOM_SECOND("自定义学段二年级",122),
    CUSTOM_THIRD("自定义学段三年级",123),
    CUSTOM_FORTH("自定义学段四年级",124),
    CUSTOM_FIFTH("自定义学段五年级",125),
    CUSTOM_SIX("自定义学段六年级",126),
    CUSTOM_SEVENTH("自定义学段七年级",127),
    CUSTOM_EIGHT("自定义学段八年级",128),
    CUSTOM_NINTH ("自定义学段九年级",129),
    CUSTOM_TENTH("自定义学段十年级",130),
    CUSTOM_ELEVENTH("自定义学段十一年级",131),
    CUSTOM_TWELFTH("自定义学段十二年级",132),
    ;
    //@formatter:on
    /**
     * 年级描述
     */
    private final String gradeName;

    /**
     * 年级
     */
    private final int grade;

    private static final Map<Integer, GradeEnum> GRADE_MAP;

    GradeEnum(String gradeName, int grade) {
        this.grade = grade;
        this.gradeName = gradeName;
    }

    static {
        GRADE_MAP = Arrays.stream(GradeEnum.values()).collect(Collectors.toMap(GradeEnum::getGrade, v -> v));
    }

    public static GradeEnum of(int grade) {
        return GRADE_MAP.getOrDefault(grade, UNKNOWN);
    }
}
