package org.example.practice.practiceknowbox.common.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yijiu.chen
 * @date 2020/05/15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QpsLimiter {

    /**
     * qps
     *
     * @return
     */
    int qps() default 2000;
}
