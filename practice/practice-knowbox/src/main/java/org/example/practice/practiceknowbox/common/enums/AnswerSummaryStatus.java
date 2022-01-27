package org.example.practice.practiceknowbox.common.enums;

import lombok.Getter;

/**
 * @author yijiu.chen
 * @date 2020/04/26
 */
@Getter
public enum AnswerSummaryStatus {

    //@formatter:off
    NOT_SUBMIT(0, "未提交"), SUBMIT(1, "已提交"), AFTER_SUBMIT(2, "已补交");
    //@formatter:on

    private int value;

    private String desc;

    AnswerSummaryStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
