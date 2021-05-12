package org.example.practicescaffold.jdbctemplate.client;

import org.example.practicescaffold.jdbctemplate.dao.mapper.AccountRowMapper;
import org.example.practicescaffold.jdbctemplate.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 *
 */
public class JdbcTemplateDemo3 {
    public static void main(String[] args) {

        ApplicationContext as = new ClassPathXmlApplicationContext("classpath:mysql/bean.xml");
        JdbcTemplate jt = as.getBean("jdbcTemplate", JdbcTemplate.class);

        List<Account> accounts = jt.query("select * from account where id=?", new AccountRowMapper(), 1);
        System.out.println(accounts.isEmpty()?"没有内容":accounts.get(0));
        System.out.println("=======================");
    }
}
