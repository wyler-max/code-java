package org.example.practicescaffold.common.model.errorcode;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum OrderErrorCode implements ErrorCode {

    SUCCESS(0, "成功"),
    ORDER_NOT_EXIST(1, "订单不存在"),
    ORDER_TIMEOUT(2, "订单超时"),
    ORDER_EXPIRED(3, "订单已过期"),
    ORDER_SUCCESS_AND_WAIT(4, "下单成功，后台正在处理中..."),
    ORDER_FAIL(5, "下单失败，请重新下单"),

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
        return Module.MODULE_02;
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
