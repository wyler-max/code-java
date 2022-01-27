package org.example.practice.practiceknowbox.common.enums;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

/**
 * @author yijiu.chen
 * @date 2021/12/20
 */
@Getter
public enum GroupHomeworkCategory {
    //@formatter:off
    DEFAULT("默认", 0, 61),
    SUDOKU("数独", 1, 67),
    POEM("诗词", 2, 68);
    //@formatter:on

    private String name;
    private int category;
    private int questionType;

    private static Map<Integer, GroupHomeworkCategory> map;

    static {
        map = Maps.newHashMap();
        for (GroupHomeworkCategory item : values()) {
            map.put(item.getCategory(), item);
        }
    }

    GroupHomeworkCategory(String name, int category, int questionType) {
        this.name = name;
        this.category = category;
        this.questionType = questionType;
    }

    public static GroupHomeworkCategory valueOf(int category) {
        return map.getOrDefault(category, DEFAULT);
    }
}
