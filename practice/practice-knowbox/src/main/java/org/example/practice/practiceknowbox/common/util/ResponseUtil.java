package org.example.practice.practiceknowbox.common.util;

import java.util.List;

import org.example.practice.practiceknowbox.common.enums.ErrorCode;
import org.example.practice.practiceknowbox.common.model.Response;

/**
 * 常用的接口返回数据处理
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
public class ResponseUtil {

    public static <T> Response<T> makeFail(String message) {
        return makeResponse(ErrorCode.UNKNOWN.getCode(), message, null);
    }

    public static <T> Response<T> makeFail(T obj) {
        return makeResponse(ErrorCode.UNKNOWN, obj);
    }

    public static <T> Response<T> makeSuccess(T obj) {
        return makeResponse(ErrorCode.SUCCESS, obj);
    }

    public static <T> Response<T> makeSuccess(T obj, String msg) {
        return makeResponse(ErrorCode.SUCCESS.getCode(), msg, obj);
    }

    public static <T> Response<T> makeResponse(ErrorCode errorCode, T obj) {
        return makeResponse(errorCode.getCode(), errorCode.getMsg(), obj);
    }

    public static <T> Response<T> makeError(ErrorCode errorCode) {
        return makeResponse(errorCode.getCode(), errorCode.getMsg(), null);
    }

    public static <T> Response<T> makeError(ErrorCode errorCode, String msg) {
        return makeResponse(errorCode.getCode(), msg, null);
    }

    public static <T> Response<T> makeResponse(int code, String msg) {
        return makeResponse(code, msg, (T)null);
    }

    public static <T> Response<T> makeResponse(int code, String msg, T obj) {
        Response<T> result = new Response<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(obj);

        return result;
    }

    public static <T> String makeErrorResponseJson(ErrorCode errorCode) {
        Response<T> errorResponse = makeError(errorCode);
        return makeResponseJson(errorResponse);
    }

    public static <T> String makeResponseJson(Response<T> response) {
        return JsonUtil.toJson(response);
    }

    public static boolean isOk(Response<?> response) {
        return response != null && response.getCode() == ErrorCode.SUCCESS.getCode();
    }

    public static <T> T getRpcData(Response<T> res) {
        if (isOk(res)) {
            return res.getData();
        }
        return null;
    }

    public static <T> List<T> getRpcListData(Response<List<T>> res) {
        if (isOk(res)) {
            return res.getData();
        }
        return null;
    }
}
