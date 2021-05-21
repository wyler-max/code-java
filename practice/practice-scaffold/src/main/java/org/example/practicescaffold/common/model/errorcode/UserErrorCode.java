package org.example.practicescaffold.common.model.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {

    // @formatter:off
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),

    USER_NO_LOGIN(10, "未登录或已过期"),
    USER_NOT_EXIST(11, "用户不存在"),
    USER_INFO_ERROR(12, "用户信息错误"),
    USER_NAME_ERROR(13, "用户名为空"),

    UNKNOWN(9999, "未知错误");
    // @formatter:on

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
        return Module.MODULE_03;
    }

    @Override
    public String getErrorMsg() {
        return this.msg;
    }

    @Override
    public int getErrorCode() {
        return this.code;
    }
}
