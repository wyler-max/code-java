package org.example.practice.practiceknowbox.common;

import lombok.Data;

/**
 * KnowBox统一响应
 */
@Data
public class CommonResponseEntity {
    private int code;
    private String msg;
    private Object data;

    public boolean isSuccess() {
        return getCode() == 0;
    }
}
