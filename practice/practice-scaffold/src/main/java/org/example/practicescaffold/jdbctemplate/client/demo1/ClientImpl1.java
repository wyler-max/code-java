package org.example.practicescaffold.jdbctemplate.client.demo1;

import org.example.practicescaffold.jdbctemplate.dao.IAccountDao;
import org.example.practicescaffold.jdbctemplate.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 */
public class ClientImpl1 {
    public static void main(String[] args) {
        ApplicationContext as = new ClassPathXmlApplicationContext("classpath:mysql/bean_1.xml");
        IAccountDao accountDao = as.getBean("accountDao1", IAccountDao.class);

        Account account = accountDao.findAccountById(1);
        System.out.println(account);

        Account acountByName = accountDao.findAccountByName("bbb");
        System.out.println(acountByName);

        acountByName.setMoney(1200f);
        accountDao.updateAccount(acountByName);
    }
}
