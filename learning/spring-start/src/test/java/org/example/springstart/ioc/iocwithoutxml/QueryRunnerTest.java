package org.example.springstart.ioc.iocwithoutxml;

import org.example.springstart.ioc.iocwithoutxml.config.SpringConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * QueryRunnerTest
 */
public class QueryRunnerTest {

    @Test
    public void testQueryRunner() {
        // 1.2 使用注解获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        // 2.从bean容器中获取
        QueryRunner runner1 = ac.getBean("runner", QueryRunner.class);
        QueryRunner runner2 = ac.getBean("runner", QueryRunner.class);
        System.out.println(runner1 == runner2);
    }
}
