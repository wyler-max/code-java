package org.example.practice.practiceknowbox.common.enums;

import java.util.Map;
import java.util.Optional;

import com.google.common.collect.Maps;

import lombok.Getter;

/**
 * @author zhangshuai
 * @date 2020/4/24 10:46 上午
 */
@Getter
public enum Subject {

    // 科目
    MATH(0, "数学"), CHINESE(1, "语文"), ENGLISH(2, "英语"), UNKNOWN(-1, "未知"),;

    private int value;

    private String desc;

    private static Map<Integer, Subject> MAP;

    static {
        Map<Integer, Subject> tmp = Maps.newHashMap();
        for (Subject s : Subject.values()) {
            tmp.put(s.getValue(), s);
        }
        MAP = tmp;
    }

    Subject(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static final Subject of(int value) {
        return Optional.ofNullable(MAP.get(value)).orElse(UNKNOWN);
    }
}
