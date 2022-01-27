package org.example.practice.practiceknowbox.common.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 强制使用master
 *
 * @author yijiu.chen
 * @date 2020/04/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UseMasters {
    UseMaster[] value();
}
