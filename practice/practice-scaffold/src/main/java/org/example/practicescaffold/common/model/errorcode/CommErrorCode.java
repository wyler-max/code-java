package org.example.practicescaffold.common.model.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum CommErrorCode implements ErrorCode {

    SUCCESS(0, "成功"),
    UNKNOWN(1, "未知错误"),
    PARAM_INVALID(2, "请求的参数错误"),
    REQUEST_TO_OFEN(3, "请求太快了"),

    E400_BAD_REQUEST(400, "请求不合法"),
    E404_NOT_FOUND(404, "请求地址不存在"),
    E500_SERVER_EXCEPTION(500, "服务器开小差"),

    USER_NO_LOGIN(10001, "用户未登录"),
    ;

    /**
     * 错误码
     */
    @Getter
    @Setter
    private int code;

    /**
     * 错误码描述
     */
    @Getter
    @Setter
    private String msg;

    @Override
    public Module getModule() {
        return Module.COMMON;
    }

    @Override
    public String getErrorMsg() {
        return this.msg;
    }

    @Override
    public int getModuleErrorCode() {
        return this.code;
    }
}
