package org.example.practice.practiceknowbox.common;

import lombok.Data;

/**
 * 统一响应
 */
@Data
public class CommonResponse<T> {
    private int code;
    private String msg;
    private T data;

    private String requestId;

    public boolean isSuccess() {
        return getCode() == 0;
    }
}
