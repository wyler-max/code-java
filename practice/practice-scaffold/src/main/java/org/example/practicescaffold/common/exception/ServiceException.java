package org.example.practicescaffold.common.exception;

import java.io.Serializable;

import org.example.practicescaffold.common.errorcode.CommErrorCode;
import org.example.practicescaffold.common.errorcode.ErrorCode;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;
    private Object result;

    public ServiceException(ErrorCode errorCode) {
        super(errorCode.getErrorMsg());
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, Object result) {
        super(errorCode.getErrorMsg());
        this.errorCode = errorCode;
        this.result = result;
    }

    public ServiceException(ErrorCode errorCode, String message, Object result) {
        super(message);
        this.errorCode = errorCode;
        this.result = result;
    }

    public ServiceException(ErrorCode errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, String message, Object result, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.result = result;
    }

    // 指定业务异常错误码
    public static ServiceException of(ErrorCode errorCode) {
        return new ServiceException(errorCode);
    }

    public static ServiceException of(ErrorCode errorCode, Object result) {
        return new ServiceException(errorCode, result);
    }

    public static ServiceException of(ErrorCode errorCode, String message, Object result) {
        return new ServiceException(errorCode, message, result);
    }

    public static ServiceException of(ErrorCode errorCode, String message, Throwable cause) {
        return new ServiceException(errorCode, message, cause);
    }

    public static ServiceException of(ErrorCode errorCode, String message, Object result, Throwable cause) {
        return new ServiceException(errorCode, message, result, cause);
    }

    // 截断业务异常
    public static ServiceException ofSuccess(String message) {
        return new ServiceException(CommErrorCode.SUCCESS, message);
    }

    public static ServiceException ofFail(String message) {
        return new ServiceException(CommErrorCode.FAIL, message);
    }
}
