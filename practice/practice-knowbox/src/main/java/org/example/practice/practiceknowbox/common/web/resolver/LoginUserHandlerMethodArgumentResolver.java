package org.example.practice.practiceknowbox.common.web.resolver;

import org.example.practice.practiceknowbox.common.model.User;
import org.example.practice.practiceknowbox.common.model.UserContext;
import org.example.practice.practiceknowbox.common.web.annotation.LoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * @author yijiu.chen
 * @date 2020/04/17
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class)
            && parameter.hasParameterAnnotation(LoginUser.class);
    }


    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return UserContext.getUserInfo();
    }

}
