package org.example.practice.practiceknowbox.common.enums;

import lombok.Getter;

/**
 * @author yijiu.chen
 * @date 2020/04/26
 */
@Getter
public enum HomeworkStatus {

    SENDING(0, "发送中"), SEND_SUCCESS(1, "发送成功"), SEND_FAILED(2, "发送失败"),;

    private int value;
    private String desc;

    HomeworkStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
