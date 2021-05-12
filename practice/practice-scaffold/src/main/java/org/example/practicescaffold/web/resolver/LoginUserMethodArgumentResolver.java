package org.example.practicescaffold.web.resolver;

import org.example.practicescaffold.common.model.UserContext;
import org.example.practicescaffold.common.model.UserInfo;
import org.example.practicescaffold.web.annotation.LoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 用户 @LoginUser 注解解析器
 *
 * 1、supportsParameter 校验条件，当返回true时，进入resolveArgument方法做参数处理
 * 2、resolveArgument 参数处理，将返回值赋值给 @LoginUser 注解标记的 Object （UserInfo user）
 */
public class LoginUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserInfo.class)
                && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //return (UserInfo)webRequest.getAttribute(Constant.LOGIN_USER, RequestAttributes.SCOPE_REQUEST);
        return UserContext.getUser();
    }
}
