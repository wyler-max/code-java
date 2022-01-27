package org.example.practice.practiceknowbox.common.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangshuai
 * @date 2021/10/27 4:03 下午
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QywxRequestBody {

    /**
     * false表示可以为null
     * @return
     */
    boolean required() default true;
}
