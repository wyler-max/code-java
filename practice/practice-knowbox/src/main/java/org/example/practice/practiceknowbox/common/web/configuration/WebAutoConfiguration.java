package org.example.practice.practiceknowbox.common.web.configuration;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.example.practice.practiceknowbox.common.web.interceptor.CommitInterceptor;
import org.example.practice.practiceknowbox.common.web.interceptor.LogInterceptor;
import org.example.practice.practiceknowbox.common.web.interceptor.LoginInterceptor;
import org.example.practice.practiceknowbox.common.web.interceptor.QpsLimitInterceptor;
import org.example.practice.practiceknowbox.common.web.resolver.LoginUserHandlerMethodArgumentResolver;
import org.example.practice.practiceknowbox.common.web.resolver.QywxXmlHttpMessageConvert;
import org.example.practice.practiceknowbox.common.web.resolver.QywxXmlMethodProcessor;
import org.example.practice.practiceknowbox.common.web.resolver.UserTokenHandlerMethodArgumentResolver;
import org.example.practice.practiceknowbox.common.web.server.HomeworkErrorPageRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.ControllerAdviceBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author yijiu.chen
 * @date 2020/04/16
 */
@Configuration
public class WebAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new HomeworkErrorPageRegistrar();
    }

    @Bean
    @ConditionalOnMissingBean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public CommitInterceptor commitInterceptor() {
        return new CommitInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public QpsLimitInterceptor qpsLimitInterceptor() {
        return new QpsLimitInterceptor();
    }

    /*@Bean
    @ConditionalOnMissingBean
    public QywxInterceptor qywxInterceptor() {
        return new QywxInterceptor();
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(applicationContext.getBean(LogInterceptor.class)).addPathPatterns("/**");
        registry.addInterceptor(applicationContext.getBean(LoginInterceptor.class)).addPathPatterns("/**");
        registry.addInterceptor(applicationContext.getBean(QpsLimitInterceptor.class)).addPathPatterns("/**");
        registry.addInterceptor(applicationContext.getBean(CommitInterceptor.class)).addPathPatterns("/**");
        //registry.addInterceptor(applicationContext.getBean(QywxInterceptor.class)).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginUserHandlerMethodArgumentResolver());
        argumentResolvers.add(new UserTokenHandlerMethodArgumentResolver());
        List<ControllerAdviceBean> annotatedBeans = ControllerAdviceBean.findAnnotatedBeans(applicationContext);
        // 判断是否是RequestBodyAdvice、ResponseBodyAdvice
        List<Object> adviceBeans =
            annotatedBeans.stream().filter(p -> p.getBeanType() != null).filter(p -> RequestBodyAdvice.class.isAssignableFrom(p.getBeanType()) || ResponseBodyAdvice.class.isAssignableFrom(p.getBeanType())).
                collect(Collectors.toList());
        argumentResolvers.add(new QywxXmlMethodProcessor(Collections.singletonList(new QywxXmlHttpMessageConvert()),
            adviceBeans, applicationContext));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS").allowCredentials(true)
            .allowedHeaders("*").maxAge(3600);
    }
}
