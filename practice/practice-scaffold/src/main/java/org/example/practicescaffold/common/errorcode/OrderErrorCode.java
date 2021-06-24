package org.example.practicescaffold.common.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum OrderErrorCode implements ErrorCode {

    // @formatter:off
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),

    ORDER_NOT_EXIST(10, "订单不存在"),
    ORDER_TIMEOUT(11, "订单超时"),
    ORDER_EXPIRED(12, "订单已过期"),
    ORDER_SUCCESS_AND_WAIT(13, "下单成功，后台正在处理中..."),
    ORDER_FAIL(14, "下单失败，请重新下单"),

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
        return Module.MODULE_02;
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
