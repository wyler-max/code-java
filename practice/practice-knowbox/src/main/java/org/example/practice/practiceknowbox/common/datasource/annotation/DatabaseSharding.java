package org.example.practice.practiceknowbox.common.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用分库
 *
 * @author yijiu.chen
 * @date 2020/04/18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseSharding {
    /**
     * 需指定数据名称
     *
     * @return
     */
    String dbName();
}
