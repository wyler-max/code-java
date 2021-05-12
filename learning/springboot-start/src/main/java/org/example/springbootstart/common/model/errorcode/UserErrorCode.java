package org.example.springbootstart.common.model.errorcode;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {

    SUCCESS(0, "成功"),
    USER_NOT_EXIST(1, "用户不存在"),
    USER_INFO_ERROR(2, "用户信息错误"),
    USER_NAME_ERROR(3, "用户名为空"),

    UNKNOWN(99999, "未知错误");

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
    public int getModuleErrorCode() {
        return this.code;
    }
}
