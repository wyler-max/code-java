package org.example.practice.practiceknowbox.common.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 所有接口返回通用对象
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
@Data
public class Response<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    public Response() {}

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
