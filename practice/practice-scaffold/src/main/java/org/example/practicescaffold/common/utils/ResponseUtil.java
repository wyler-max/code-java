package org.example.practicescaffold.common.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.example.practicescaffold.common.model.Response;
import org.example.practicescaffold.common.model.errorcode.ErrorCode;

/**
 * 返回值处理工具类
 */
public class ResponseUtil {

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int UNKNOWN = 99999;

    private static final String EMPTY = StringUtils.EMPTY;

    // base makeResponse
    public static <T> Response<T> makeResponse(int code, String msg, T data) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> makeResponse(int code, String msg) {
        return makeResponse(code, msg, null);
    }

    public static <T> Response<T> makeResponse(ErrorCode errorCode) {
        return makeResponse(errorCode.getErrorCode(), errorCode.getErrorMsg(), null);
    }

    public static <T> Response<T> makeResponse(ErrorCode errorCode, T data) {
        return makeResponse(errorCode.getErrorCode(), errorCode.getErrorMsg(), data);
    }

    // success
    public static <T> Response<T> makeSuccess(String msg) {
        return makeResponse(SUCCESS, msg, null);
    }

    public static <T> Response<T> makeSuccess(T data) {
        return makeResponse(SUCCESS, EMPTY, data);
    }

    // fail
    public static <T> Response<T> makeFail(String msg) {
        return makeResponse(FAIL, msg, null);
    }

    public static <T> Response<T> makeFail(T data) {
        return makeResponse(FAIL, EMPTY, data);
    }

    public static <T> Response<T> makeFail(ErrorCode errorCode) {
        return makeResponse(errorCode.getGlobalErrorCode(), errorCode.getErrorMsg(), null);
    }

    public static <T> Response<T> makeFail(ErrorCode errorCode, T data) {
        return makeResponse(errorCode.getGlobalErrorCode(), errorCode.getErrorMsg(), data);
    }

    public static <T> Response<T> makeFail(ErrorCode errorCode, String msg) {
        return makeResponse(errorCode.getGlobalErrorCode(), msg, null);
    }

    // 转Json
    public static <T> String makeFailResponseJson(ErrorCode errorCode) {
        Response<T> errorResponse = makeFail(errorCode);
        return makeResponseJson(errorResponse);
    }

    public static <T> String makeResponseJson(Response<T> response) {
        return JsonUtil.toJson(response);
    }

    // rpc response
    public static boolean isSuccess(Response<?> response) {
        return response != null && response.getCode() == SUCCESS;
    }

    public static <T> T getRpcData(Response<T> response) {
        if (isSuccess(response)) {
            return response.getData();
        }
        return null;
    }

    public static <T> List<T> getRpcListData(Response<List<T>> response) {
        if (isSuccess(response)) {
            return response.getData();
        }
        return null;
    }
}
