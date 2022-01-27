package org.example.practice.practiceknowbox.common.excpetion;

import java.io.Serializable;
import java.text.MessageFormat;

import org.example.practice.practiceknowbox.common.enums.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务通用异常，可以透传，不熔断
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    public ServiceException(int errorCode, String errMsg) {
        super(errMsg);
        this.code = errorCode;
        this.msg = errMsg;
    }

    public ServiceException(String errMsg) {
        super(errMsg);
        this.code = ErrorCode.UNKNOWN.getCode();
        this.msg = errMsg;
    }

    public ServiceException(ErrorCode code) {
        super(code.getMsg());
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public ServiceException(ErrorCode code, Object... args) {
        super(MessageFormat.format(code.getMsg(), args));
        this.code = code.getCode();
        this.msg = super.getMessage();
    }

}
