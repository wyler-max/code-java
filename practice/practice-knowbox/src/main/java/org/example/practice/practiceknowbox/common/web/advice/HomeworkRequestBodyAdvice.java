package org.example.practice.practiceknowbox.common.web.advice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.practice.practiceknowbox.common.model.AccessInfo;
import org.example.practice.practiceknowbox.common.model.BusinessParam;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

/**
 * 请求参数
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
@ControllerAdvice(basePackages = {"org.example.practice.practiceknowbox"})
public class HomeworkRequestBodyAdvice implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
        Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
        Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
        Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        AccessInfo accessInfo = AccessInfo.getRequestInfo();
        if (null != accessInfo) {
            String body = IOUtils.toString(inputMessage.getBody(), CharEncoding.UTF_8);
            try {
                accessInfo.addAllParam("REQUEST_BODY", body);
            } catch (Exception e) {
                // do nothing
            }
            return new MappingJacksonInputMessage(new ByteArrayInputStream(body.getBytes()), inputMessage.getHeaders());
        }
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
        Class<? extends HttpMessageConverter<?>> converterType) {
        if (body != null && body instanceof BusinessParam) {
            BusinessParam param = (BusinessParam)body;
            AccessInfo requestInfo = AccessInfo.getRequestInfo();
            if (StringUtils.isBlank(param.getBuild_time()) && requestInfo != null) {
                String build_time = Optional.ofNullable(requestInfo.getParamMap())
                    .map(f -> f.getOrDefault("build_time", "0")).orElse("0");
                param.setBuild_time(build_time);
            }
        }
        return body;
    }
}
