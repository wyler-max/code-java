package org.example.practicescaffold.config.mysql.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用分表，支持单表，注意索要数据源的需要分表的表名不能重复
 * 
 * @author yijiu.chen
 * @date 2020/04/18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableSharding {

    /**
     * 需要指定表名称
     * 
     * @return
     */
    String tableName();

    /**
     * db数据名称
     * 
     * @return
     */
    String dbName();
}
