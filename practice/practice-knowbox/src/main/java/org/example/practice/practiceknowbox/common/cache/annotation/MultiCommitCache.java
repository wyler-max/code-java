package org.example.practice.practiceknowbox.common.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangshuai
 * @date 2020/4/30 11:02 上午
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiCommitCache {

    /**
     * 窗口大小(单位:s), 默认1s。推荐可以被60整除
     *
     * @return
     */
    int delta() default 5;

    /**
     * 阈值, 每次窗口期内允许请求的次数
     *
     * @return
     */
    int threshold() default 1;

}
