package org.example.practicescaffold.jdbctemplate.client;

import org.example.practicescaffold.jdbctemplate.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 使用 RowMapper 的方式：常用的方式
 * 使用 ResultSetExtractor 的方式:不常用的方式
 */
public class JdbcTemplateDemo2 {
    public static void main(String[] args) {
        ApplicationContext as = new ClassPathXmlApplicationContext("classpath:mysql/bean.xml");
        JdbcTemplate jt = as.getBean("jdbcTemplate", JdbcTemplate.class);

        // BeanPropertyRowMapper
        List<Account> accounts = jt.query("select * from account where id=?", new BeanPropertyRowMapper<>(Account.class), 1);
        System.out.println(accounts.isEmpty()?"没有内容":accounts.get(0));
        System.out.println("=======================");

        /*List<Account> accountAll = jt.query("select * from account", new BeanPropertyRowMapper<>(Account.class));
        if (accountAll.isEmpty()) {
            System.out.println("没有内容");
        } else {
            accountAll.forEach(account -> {
                System.out.println(account);
            });
        }*/

        //jt.update("insert into account(name,money)values(?,?)","eee",3333f);

        Long count = jt.queryForObject("select count(*) from account where money > ?", Long.class, 1000F);
        System.out.println(count);
    }
}
