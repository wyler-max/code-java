package org.example.practicescaffold.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.practicescaffold.common.errorcode.ErrorCode;

import java.io.Serializable;

/**
 * @author liulang@knowbox.cn
 * @version 1.0
 * @desc REST结果
 * @date 2018/6/7
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestResponse<T> implements Serializable {

    private static final long serialVersionUID = 2324429034443434545L;

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;

    private int code;
    private T data;
    private String msg;

    public boolean ok() {
        return getCode() == SUCCESS;
    }

    public static <T> RestResponse<T> of(final ErrorCode code, final T result) {
        return new RestResponse<>(code.getGlobalErrorCode(), result, code.getErrorMsg());
    }

    public static <T> RestResponse<T> of(final ErrorCode code, final T result, final String msg) {
        return new RestResponse<>(code.getGlobalErrorCode(), result, msg);
    }

    public static <T> RestResponse<T> of(final int code, final T result, final String msg) {
        return new RestResponse<>(code, result, msg);
    }

    public static <T> RestResponse<T> success(final T result) {
        return new RestResponse<T>(SUCCESS, result, "");
    }

    public static <T> RestResponse<T> success(final T result, final String msg) {
        return new RestResponse<T>(SUCCESS, result, msg);
    }

    public static <T> RestResponse<T> success(final String msg) {
        return new RestResponse<T>(SUCCESS, null, msg);
    }

    public static <T> RestResponse<T> success() {
        return new RestResponse<T>(SUCCESS, null, "success");
    }

    public static <T> RestResponse<T> fail(final T result, final String msg) {
        return new RestResponse<T>(FAIL, result, msg);
    }

    public static <T> RestResponse<T> fail(final int code, final T result, final String msg) {
        return new RestResponse<T>(code, result, msg);
    }

    public static <T> RestResponse<T> fail(final String msg) {
        return new RestResponse<T>(FAIL, null, msg);
    }

    public static <T> RestResponse<T> fail() {
        return new RestResponse<T>(FAIL, null, "error");
    }

    public static <T> RestResponse<T> fail(final ErrorCode errorCode) {
        return new RestResponse<T>(errorCode.getGlobalErrorCode(), null, errorCode.getErrorMsg());
    }
}
