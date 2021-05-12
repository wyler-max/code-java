package org.example.springbootstart.common.model;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
public enum ErrorCode {

    // 系统0~9999
    SUCCESS(0, "成功"),
    UNKNOWN(1, "未知错误"),

    REQ_PARAM_INVALID(2, "请求参数错误"),
    REQ_FREQUENTLY(3, "请求太过频繁"),

    // HTTP状态码
    E302_REQUEST_REDIECT(302, "请求重定向"),
    E400_BAD_REQUEST(400, "请求不合法"),
    E404_NOT_FOUND(404, "请求地址不存在"),
    E500_SERVER_EXCEPTION(500, "服务器开小差"),
    E503_SERVER_OVERLOAD(503, "服务器过载"),

    // 用户10000~19999
    USER_NO_LOGIN(10001, "用户未登录"),
    TOKEN_INVAILD(10002, "无效登录信息"),
    TOKEN_EXPIRED(10003, "登录信息已失效"),

    // 订单20000~29999
    ORDER_NOT_EXIST(1, "订单不存在"),
    ORDER_EXPIRED(2, "订单已过期"),
    ORDER_SUCCESS_AND_WAIT(3, "下单成功，后台正在处理中..."),
    ORDER_FAIL(4, "下单失败，请重新下单"),
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

    private static final Map<Integer, ErrorCode> map = Maps.newHashMap();
    static {
        for (ErrorCode errorCode: ErrorCode.values()) {
            map.put(errorCode.getCode(), errorCode);
        }
    }
    public ErrorCode valueOf(int code) {
        if (map.containsKey(code)) {
            return map.get(code);
        }
        return UNKNOWN;
    }
}
