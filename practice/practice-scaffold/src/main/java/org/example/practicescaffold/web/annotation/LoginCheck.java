package org.example.practicescaffold.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 检查登录状态
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCheck {

    /**
     * 检查用户登录状态
     * @return
     */
    boolean check() default true;

    /**
     * 声明获取用户信息
     * @return
     */
    boolean fetchUser() default true;
}
