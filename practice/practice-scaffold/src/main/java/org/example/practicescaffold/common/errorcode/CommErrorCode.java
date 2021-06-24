package org.example.practicescaffold.common.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum CommErrorCode implements ErrorCode {

    // @formatter:off
    /**
     * 基础状态码
     */
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),

    REQ_PARAM_INVALID(2, "请求参数错误"),
    REQ_FREQUENTLY(3, "请求太过频繁"),

    /**
     * HTTP状态码
     * @see org.springframework.http.HttpStatus
     */
    E301_MOVED_PERMANENTLY(301, "301永久跳转"),
    E302_REQUEST_REDIECT(302, "请求重定向"),
    E400_BAD_REQUEST(400, "请求不合法"),
    E404_NOT_FOUND(404, "请求地址不存在"),
    E500_SERVER_EXCEPTION(500, "服务器开小差"),
    E503_SERVER_OVERLOAD(503, "服务器过载"),

    /**
     * 用户状态码
     */
    USER_NO_LOGIN(10001, "用户未登录"),
    UNKNOWN(99999, "未知错误");
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
        return Module.COMMON;
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
