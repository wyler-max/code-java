package org.example.practicescaffold.web.interceptor;


import org.apache.commons.lang3.math.NumberUtils;
import org.example.practicescaffold.common.model.AccessLogInfo;
import org.example.practicescaffold.common.utils.HttpUtil;
import org.example.practicescaffold.common.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * 通用业务日志拦截器
 */
public class LogInterceptor implements HandlerInterceptor {
    public static final String USER_ID = "__live_user_id__";

    /**
     * app 所属app
     */
    private String appName;

    /**
     * 按服务初始化
     */
    public LogInterceptor(String appName) {
        this.appName = appName;
    }


    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

    private static Logger ACCESS_LOGGER = LoggerFactory.getLogger("accessLogger");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        AccessLogInfo info = new AccessLogInfo();
        info.setAppName(appName);
        if ((handler instanceof HandlerMethod)) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            info.setControllerInfo(method.getDeclaringClass().getSimpleName() + "." + method.getName());
        } else {
            // ZUUL ROUTER
            info.setControllerInfo(handler.getClass().getSimpleName());
        }

        info.setCookies(HttpUtil.fetchCookieString(request));
        Map<String, String> headerMap = HttpUtil.fetchHeaders(request);
        info.setHeaderMap(headerMap);
        info.setTime(new Date());
        info.setIp(IpUtil.getRealIP(request));
        info.setMethod(request.getMethod());
        info.setParamMap(HttpUtil.fetchParameters(request));
        info.setUri(request.getRequestURI());
        info.setUriPattern(String.valueOf(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)));
        try {
            String forwardPath = (String)request.getAttribute(RequestDispatcher.FORWARD_SERVLET_PATH);
            if (forwardPath != null) {
                info.getParamMap().put(RequestDispatcher.FORWARD_SERVLET_PATH, forwardPath);
            }
        } catch (Exception e) {
            // do nothing
        }
        // 先取header，后续根据sessionId覆盖
        info.setUserId(NumberUtils.toLong(headerMap.get(USER_ID)));
        AccessLogInfo.setRequestInfo(info);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        AccessLogInfo info = AccessLogInfo.getRequestInfo();
        if (info != null) {
            info.setCostTime(System.currentTimeMillis() - info.getTime().getTime());
            ACCESS_LOGGER.info(info.toStr());
            AccessLogInfo.removeRequestInfo();
        }
    }
}
