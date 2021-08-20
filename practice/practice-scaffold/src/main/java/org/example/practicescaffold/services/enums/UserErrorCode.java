package org.example.practicescaffold.services.enums;

import org.example.practicescaffold.common.errorcode.ErrorCode;
import org.example.practicescaffold.common.errorcode.Module;

import lombok.Getter;
import lombok.Setter;

public enum UserErrorCode implements ErrorCode {
    SUCCESS(0, "成功"), FAIL(1, "失败"), USER_A(2, "user-a"), USER_B(2, "user-b");

    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String msg;

    UserErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Module getModule() {
        return Module.MODULE_01;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }

    @Override
    public int getErrorCode() {
        return code;
    }
}
