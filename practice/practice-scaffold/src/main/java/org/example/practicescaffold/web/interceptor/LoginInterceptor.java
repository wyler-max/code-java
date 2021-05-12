package org.example.practicescaffold.web.interceptor;

import org.example.practicescaffold.common.model.ErrorCode;
import org.example.practicescaffold.common.model.UserContext;
import org.example.practicescaffold.common.model.UserInfo;
import org.example.practicescaffold.common.utils.HttpUtil;
import org.example.practicescaffold.common.utils.MockUtil;
import org.example.practicescaffold.common.utils.ResponseUtil;
import org.example.practicescaffold.db.model.biz.User;
import org.example.practicescaffold.web.annotation.LoginCheck;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        LoginCheck check = null;
        if (method.isAnnotationPresent(LoginCheck.class)) {
            check = method.getAnnotation(LoginCheck.class);
        } else if (method.getDeclaringClass().isAnnotationPresent(LoginCheck.class)) {
            check = method.getDeclaringClass().getAnnotation(LoginCheck.class);
        }
        if (check == null || (!check.check() && !check.fetchUser())) {
            return true;
        }
        User user = null;
        // 将fetchUser 和 check 功能分离，方便处理只要用户信息，不做强制登录态校验的逻辑
        if (check.fetchUser()) {
            // 根据TOKEN获取登录用户信息
            String token = HttpUtil.fetchHeaderOrParameterByKey(request, "token");
            if (StringUtils.isNotBlank(token) /*&& TokenUtil.verify(token)*/) {
                user = MockUtil.fetchUserByToken(token);
            }
            if (user != null) {
                UserInfo userInfo = new UserInfo();
                BeanUtils.copyProperties(user, userInfo);
                UserContext.setUser(userInfo);
            }
        }
        if (check.check() && user == null) {
            ResponseUtil.responseFail(response, ErrorCode.USER_NO_LOGIN);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.release();
    }
}
