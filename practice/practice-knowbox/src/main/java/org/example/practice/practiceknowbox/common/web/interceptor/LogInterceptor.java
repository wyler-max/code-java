package org.example.practice.practiceknowbox.common.web.interceptor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.practice.practiceknowbox.common.model.AccessInfo;
import org.example.practice.practiceknowbox.common.util.HttpUtil;
import org.example.practice.practiceknowbox.common.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 日志拦截器
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
public class LogInterceptor extends HandlerInterceptorAdapter implements Ordered {

    /**
     * 阿里云体系
     */
    public static final String REQUEST_ID = "Eagleeye-Traceid";

    /* (non-Javadoc)
     * @see org.springframework.core.Ordered#getOrder()
     */
    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

    private static Logger INVOKE_LOGGER = LoggerFactory.getLogger("accessLogger");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        MDC.put(REQUEST_ID, getRequestId(request));

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        AccessInfo info = new AccessInfo();
        info.setControllerInfo(method.getDeclaringClass().getSimpleName() + "." + method.getName());
        info.setCookies(HttpUtil.fetchCookieString(request));
        info.setHeaders(HttpUtil.fetchHeaderString(request));
        info.setTime(new Date());
        info.setIp(IpUtil.getRealIP(request));
        info.setMethod(request.getMethod());
        info.setParamMap(HttpUtil.fetchParameters(request));
        info.setUri(request.getRequestURI());
        info.setUriPattern(String.valueOf(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)));
        AccessInfo.setRequestInfo(info);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        AccessInfo info = AccessInfo.getRequestInfo();
        if (info != null) {
            info.setCostTime(System.currentTimeMillis() - info.getTime().getTime());
            INVOKE_LOGGER.info(info.toStr());
            AccessInfo.removeRequestInfo();
        }
        MDC.remove(REQUEST_ID);
    }

    private String getRequestId(HttpServletRequest request) {
        String requestId = HttpUtil.fetchHeaderOrParameterByKey(request, REQUEST_ID);
        if (StringUtils.isBlank(requestId)) {
            return DigestUtils.md5Hex(UUID.randomUUID().toString());
        }
        return requestId;
    }
}
