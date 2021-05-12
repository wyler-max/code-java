package org.example.springbootstart.common.utils;

import org.example.springbootstart.common.model.Response;
import org.example.springbootstart.common.model.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 返回值处理工具类
 */
public class ResponseUtil {

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;


    public static <T> Response<T> makeResponse(int code, String msg) {
        return makeResponse(code, msg, null);
    }
    public static <T> Response<T> makeResponse(ErrorCode errorCode, T data) {
        return makeResponse(errorCode.getCode(), errorCode.getMsg(), data);
    }
    // base makeResponse
    public static <T> Response<T> makeResponse(int code, String msg, T data) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    // success
    public static <T> Response<T> makeSuccess(String msg) {
        return makeResponse(ErrorCode.SUCCESS.getCode(), msg, null);
    }
    public static <T> Response<T> makeSuccess(T data) {
        return makeResponse(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), data);
    }
    // fail
    public static <T> Response<T> makeFail(String msg) {
        return makeResponse(ErrorCode.UNKNOWN.getCode(), msg, null);
    }
    public static <T> Response<T> makeFail(T data) {
        return makeResponse(ErrorCode.UNKNOWN.getCode(), ErrorCode.UNKNOWN.getMsg(), data);
    }
    public static <T> Response<T> makeFail(ErrorCode errorCode) {
        return makeResponse(errorCode.getCode(), errorCode.getMsg(), null);
    }
    public static <T> Response<T> makeFail(ErrorCode errorCode, String msg) {
        return makeResponse(errorCode.getCode(), msg, null);
    }
    public static <T> Response<T> makeFail(ErrorCode errorCode, T data) {
        return makeResponse(errorCode.getCode(), errorCode.getMsg(), data);
    }

    // 转Json

    // rpc response
    public static boolean isSuccess(Response<?> response) {
        return response != null && response.getCode() == ErrorCode.SUCCESS.getCode();
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

    // 传入 Response
    public static void makeResp(ServletResponse response, Response<?> restResponse) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        new ObjectMapper().writeValue(response.getWriter(), restResponse);
    }
    public static void responseFail(ServletResponse response, ErrorCode errorCode) throws IOException {
        makeResp(response, makeFail(errorCode));
    }
    public static void responseFail(ServletResponse response, String msg) throws IOException {
        makeResp(response, makeFail(msg));
    }
}
