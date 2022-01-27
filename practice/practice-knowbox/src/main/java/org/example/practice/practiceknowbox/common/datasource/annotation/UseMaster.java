package org.example.practice.practiceknowbox.common.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 强制使用master数据库
 *
 * @author yijiu.chen
 * @date 2020/04/18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(UseMasters.class)
public @interface UseMaster {
    /**
     * 数据库
     *
     * @return
     */
    String dbName();
}
