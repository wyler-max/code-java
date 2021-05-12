package org.example.springbootstart.common.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * Http工具类
 */
public class HttpUtil {
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
}
