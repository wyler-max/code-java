package org.example.practice.practiceknowbox.common.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.example.practice.practiceknowbox.common.enums.ErrorCode;
import org.example.practice.practiceknowbox.common.model.AccessInfo;
import org.example.practice.practiceknowbox.common.model.User;
import org.example.practice.practiceknowbox.common.model.UserContext;
import org.example.practice.practiceknowbox.common.service.UserClient;
import org.example.practice.practiceknowbox.common.util.HttpUtil;
import org.example.practice.practiceknowbox.common.util.ResponseUtil;
import org.example.practice.practiceknowbox.common.util.TokenUtil;
import org.example.practice.practiceknowbox.common.web.annotation.LoginInfoCheck;
import org.example.practice.practiceknowbox.common.web.resolver.UserTokenHandlerMethodArgumentResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录态拦截器
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
public class LoginInterceptor extends HandlerInterceptorAdapter
    implements InitializingBean, ApplicationContextAware, Ordered {

    @Autowired(required = false)
    private UserClient userClient;

    private ApplicationContext applicationContext;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        UserContext.release();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        LoginInfoCheck check = null;
        // 不需要登陆
        if (method.isAnnotationPresent(LoginInfoCheck.class)) {
            check = method.getAnnotation(LoginInfoCheck.class);
        } else if (method.getDeclaringClass().isAnnotationPresent(LoginInfoCheck.class)) {
            check = method.getDeclaringClass().getAnnotation(LoginInfoCheck.class);
        }
        if (check == null || (!check.check() && !check.fetchUser())) {
            return true;
        }

        User userInfo = null;
        if (check.fetchUser()) {
            // 根据TOKEN获取登录用户信息
            String token = HttpUtil.fetchHeaderOrParameterByKey(request, UserClient.TOKEN_NAME);
            if (StringUtils.isNotBlank(token) && TokenUtil.verify(token)) {
                userInfo = ResponseUtil.getRpcData(userClient.fetchUserInfoByToken(token));
            }
            if (userInfo != null) {
                UserContext.setUserInfo(userInfo);
                request.setAttribute(UserTokenHandlerMethodArgumentResolver.USER_TOKEN, token);
                AccessInfo info = AccessInfo.getRequestInfo();
                if (info != null) {
                    info.setUserId(userInfo.getId());
                }
            }
        }

        if (check.check() && userInfo == null) {
            HttpUtil.flushResponse(response, ErrorCode.NOT_LOGIN, HttpStatus.OK.value());
            return false;
        }

        return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // UserClient本地
        String[] userClientBeanNames = this.applicationContext.getBeanNamesForType(UserClient.class);
        for (String userClientBeanName : userClientBeanNames) {
            if (UserClient.LOCAL_BEAN_NAME.equals(userClientBeanName)) {
                this.userClient = (UserClient)this.applicationContext.getBean(UserClient.LOCAL_BEAN_NAME);
                break;
            }
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.core.Ordered#getOrder()
     */
    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 1;// log 后面
    }

}
