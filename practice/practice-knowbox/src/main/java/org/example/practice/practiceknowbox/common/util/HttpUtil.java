package org.example.practice.practiceknowbox.common.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.example.practice.practiceknowbox.common.enums.ErrorCode;
import org.example.practice.practiceknowbox.common.model.AccessInfo;

import com.google.common.collect.Maps;

/**
 * http请求工具类
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
public class HttpUtil {

    /**
     * 是否开启压测模式
     *
     * @param request
     * @return
     */
    public static boolean isTestRequest(HttpServletRequest request) {
        return "enable".equals(request.getParameter("tagTest"));
    }

    public static String fetchCookieString(HttpServletRequest request) {
        Map<String, String> cookieMap = Maps.newHashMap();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), StringUtils.defaultString(cookie.getValue()));
            }
        }
        return JsonUtil.toJson(cookieMap);
    }

    public static String fetchHeaderString(HttpServletRequest request) {
        Map<String, String> headerMap = Maps.newHashMap();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headerMap.put(headerName, StringUtils.defaultString(headerValue));

        }
        return JsonUtil.toJson(headerMap);
    }

    public static Map<String, String> fetchParameters(HttpServletRequest request) {
        Map<String, String> paramMap = Maps.newHashMap();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            paramMap.put(paramName, StringUtils.defaultString(paramValue));
        }
        return paramMap;
    }

    public static String fetchHeaderOrParameterByKey(HttpServletRequest request, String key) {
        String value = request.getHeader(key);
        if (StringUtils.isNotBlank(value)) {
            return value;
        }
        return request.getParameter(key);
    }

    public static void flushResponse(HttpServletResponse response, ErrorCode errorCode, int code) throws IOException {
        response.setStatus(code);
        response.setContentType("application/json; charset=UTF-8");
        response.getOutputStream()
            .write(ResponseUtil.makeErrorResponseJson(errorCode).getBytes(StandardCharsets.UTF_8));
        AccessInfo accessInfo = AccessInfo.getRequestInfo();
        if (null != accessInfo) {
            accessInfo.setCode(errorCode.getCode());
            accessInfo.setMsg(errorCode.getMsg());
        }
    }

    public static void flushResponse(HttpServletResponse response, String msg, int code) throws IOException {
        response.setStatus(code);
        response.setContentType("application/json; charset=UTF-8");
        response.getOutputStream()
            .write(msg.getBytes(StandardCharsets.UTF_8));
        //TODO 打印日志。。。
    }
}
