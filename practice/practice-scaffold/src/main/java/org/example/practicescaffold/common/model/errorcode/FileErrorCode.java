package org.example.practicescaffold.common.model.errorcode;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum FileErrorCode implements ErrorCode {

    SUCCESS(0, "成功"),
    FILE_NOT_EXIST(1, "文件不存在"),
    FILE_BROKEN(2, "文件破损"),
    FILE_UPLOAD_TIMEOUT(100, "上传文件超时"),
    FILE_UPLOAD_OVERSIZE(101, "上传文件过大"),
    FILE_UPLOAD_FAIL(102, "上传文件失败"),

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
        return Module.MODULE_01;
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
