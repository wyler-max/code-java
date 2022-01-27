package org.example.practice.practiceknowbox.common.enums;

import lombok.Getter;

/**
 * @author zhangshuai
 * @date 2020/5/20 11:44 上午
 */
@Getter
public enum AnswerSummaryRedoStatus {

    // 订正状态,0未处理，1全部订正，2部分订正，3未完全订正查看解析
    DEFAULT(0, "默认状态,未处理"),
    ALL_REVISE(1, "全部订正"),
    PARTIALLY_REVISE(2, "部分订正"),
    VIEW_ANSWER(3, "查看答案"),;

    private int status;

    private String desc;

    AnswerSummaryRedoStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
