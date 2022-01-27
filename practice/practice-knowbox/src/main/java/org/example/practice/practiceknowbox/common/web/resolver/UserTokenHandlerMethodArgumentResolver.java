package org.example.practice.practiceknowbox.common.web.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import org.example.practice.practiceknowbox.common.web.annotation.UserToken;

/**
 * @author yijiu.chen
 * @date 2020/08/03
 */
public class UserTokenHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public static final String USER_TOKEN = "HW_PC_API_USER_TOKEN";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(String.class)
            && parameter.hasParameterAnnotation(UserToken.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return (String)webRequest.getAttribute(USER_TOKEN, RequestAttributes.SCOPE_REQUEST);
    }

}
