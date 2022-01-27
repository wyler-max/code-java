package org.example.practice.practiceknowbox.common.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 多数据源事务
 *
 * @author yijiu.chen
 * @date 2020/04/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MultiTransactional {
    /**
     * 事务管理器
     *
     * @return
     */
    String[] values();

    /**
     * 默认回滚
     *
     * @return
     */
    Class<? extends Throwable> rollbackFor() default RuntimeException.class;
}
