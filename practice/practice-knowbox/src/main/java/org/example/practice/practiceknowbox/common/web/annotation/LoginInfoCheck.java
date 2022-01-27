package org.example.practice.practiceknowbox.common.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.example.practice.practiceknowbox.common.enums.ThirdUserRoleType;

/**
 * 用户获取相关
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginInfoCheck {

    /**
     * 检查是否需要用户登录
     *
     * @return
     */
    boolean check() default true;

    /**
     * 是否获取用户信息
     *
     * @return
     */
    boolean fetchUser() default true;

    /**
     * role非0时, 不需要校验用户身份
     *
     * @return
     */
    ThirdUserRoleType[] role() default {};
}
