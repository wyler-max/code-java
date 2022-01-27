package org.example.practice.practiceknowbox.common.enums;

import lombok.Getter;

/**
 * @author yijiu.chen
 * @date 2020/04/24
 */
@Getter
public enum HomeworkExerciseType {
    // @formatter:off
    DAILY(0, "日常练习"),
    UNIT(1, "单元复习"),
    // 暑假作业、寒假作业等
    GROUP(2, "聚合作业"),
    // 考试
    ABILITY(3, "能力调研"),
    REPORT_PUSH(5, "个性化作业"),
    MATCH(4, "趣味比赛"),
    POINT24(6, "24点"),
    SUDOKU(7, "数独"),
    POETRY(8, "诗词大会"),
    WORD(9, "单词比赛"),
    QUICK(10, "抢答题"),
    TEST(11, "测验"),
    SHIKAKU(12, "数方比赛"),
    REQUIRE(13, "必答题"),
    ;
    // @formatter:on

    private int type;
    private String desc;

    HomeworkExerciseType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
