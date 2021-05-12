package org.example.practicescaffold.jdbctemplate.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 */
public class JdbcTemplateDemo4 {
    public static void main(String[] args) {

        // 1. 使用Bean注入
        ApplicationContext as = new ClassPathXmlApplicationContext("classpath:mysql/bean.xml");
        JdbcTemplate jt = as.getBean("jdbcTemplate", JdbcTemplate.class);
        jt.execute("insert into account(name,money) values ('ddd', 2222)");

        // 2. 自行创建实例对象
        /*
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring");
        ds.setUsername("root");
        ds.setPassword("123456");

        JdbcTemplate jd = new JdbcTemplate();
        jd.setDataSource(ds);
        jd.execute("insert into account(name,money) values ('ddd', 2222)");
        */
    }
}
