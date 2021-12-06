package org.example.practice.practiceknowbox.entities;

import lombok.Data;

@Data
public class CommonResponse {
    private int code;
    private Object data;
    private String msg;

    private String requestId;
}
