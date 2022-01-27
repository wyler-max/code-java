package org.example.practice.practiceknowbox.common.web.advice;

import org.example.practice.practiceknowbox.common.enums.ErrorCode;
import org.example.practice.practiceknowbox.common.model.AccessInfo;
import org.example.practice.practiceknowbox.common.model.Response;
import org.example.practice.practiceknowbox.common.util.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 *
 * 响应内容
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
@ControllerAdvice(basePackages = {"cn.knowbox.homework"})
public class HomeworkResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {
        AccessInfo accessInfo = AccessInfo.getRequestInfo();
        if (null != accessInfo) {
            if (body instanceof Response) {
                Response<?> resp = (Response<?>)body;
                accessInfo.setCode(resp.getCode());
                accessInfo.setMsg(resp.getMsg());
            } else {
                accessInfo.setCode(ErrorCode.SUCCESS.getCode());
                accessInfo.setMsg(ErrorCode.SUCCESS.getMsg());
            }
            accessInfo.setResp(JsonUtil.toJson(body));
        }
        return body;
    }

}
