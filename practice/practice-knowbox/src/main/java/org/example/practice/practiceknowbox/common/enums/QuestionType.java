package org.example.practice.practiceknowbox.common.enums;

import java.util.Map;
import java.util.Optional;

import com.google.common.collect.Maps;

import lombok.Getter;

@Getter
public enum QuestionType {
    // @formatter:off
    UNKNOWN(-1, "未知题型", "UNKNOWN", 0),
    BLANK(0, "填空题", "BLANK", 1),
    CHOICE(1, "选择题", "CHOICE", 1),
    CHOICE_BLANK(2, "选词填空", "CHOICE_BLANK", 0),
    SENTENCE(3, "连词成句", "SENTENCE", 0),
    JUDGEMENT(5, "判断题", "JUDGEMENT", 1),
    PICTURE_BLANK(8, "图片挖空", "PICTURE_BLANK", 1),
    LINE(11, "连线", "LINE", 0),
    CLASSIFY(12, "分类", "CLASSIFY", 0),
    VERTICAL_ONE(15, "竖式一期", "VERTICAL_ONE", 0),
    SORT(16, "排序", "SORT", 0),
    CHOICE_AUDIO(24, "听音选择", "CHOICE_AUDIO", 1),
    SORT_AUDIO(25, "听音排序", "ZHUYIN_CLASSIFY", 0),
    AUDIO_LINE(26, "听音连线", "AUDIO_LINE", 0),
    JUDGEMENT_AUDIO(27, "听音判断", "JUDGEMENT_AUDIO", 1),
    OFF_THE_CALC_ONE(29, "脱式一期", "OFF_THE_CALC_ONE", 0),
    SUDOKU(31, "数独", "SUDOKU",0),
    POINT24(32, "24点", "POINT24", 0),
    PINYIN_CHOICE(63, "拼音选择题", "PINYIN_CHOICE", 1),
    PINYIN_CHOICE_BLANK(64, "拼音选词填空", "PINYIN_CHOICE_BLANK", 0),
    VERTICAL_TWO(69, "竖式二期", "VERTICAL_TWO", 0),
    OFF_THE_CALC_TWO(70, "脱式二期", "OFF_THE_CALC_TWO", 0),
    ZHUYIN_LINE(71, "注音连线题", "ZHUYIN_LINE", 0),
    ZHUYIN_CLASSIFY(72, "注音分类", "ZHUYIN_CLASSIFY", 0),
    ZHUYIN_SORT(73, "注音排序", "ZHUYIN_CLASSIFY", 0),
    ZHUYIN_SENTENCE(74, "注音连词成句", "ZHUYIN_SENTENCE", 0),
    ZHUYIN_JUDGEMENT(75, "注音判断题", "ZHUYIN_JUDGEMENT", 1),
    SHIKAKU(78, "数方题", "SHIKAKU", 0),

    ;
    // @formatter:on

    private int type;
    private String name;
    private String key;
    private int isGroup;

    private static Map<Integer, QuestionType> map;

    static {
        map = Maps.newHashMap();
        for (QuestionType qt : values()) {
            map.put(qt.getType(), qt);
        }
    }

    QuestionType(int type, String name, String key, int isGroup) {
        this.type = type;
        this.name = name;
        this.key = key;
        this.isGroup = isGroup;
    }

    /**
     * 是否需要分组
     *
     * @return
     */
    public static boolean isNeedGroup(int questionType) {
        return valueOf(questionType).getIsGroup() == 1;
    }

    public static QuestionType valueOf(int type) {
        return Optional.ofNullable(map.get(type)).orElse(UNKNOWN);
    }
}
