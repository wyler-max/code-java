package org.example.practicescaffold.jdbctemplate.client.demo2;

import org.example.practicescaffold.jdbctemplate.dao.IAccountDao;
import org.example.practicescaffold.jdbctemplate.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 */
public class ClientImpl2 {
    public static void main(String[] args) {
        ApplicationContext as = new ClassPathXmlApplicationContext("classpath:mysql/bean_2.xml");
        IAccountDao accountDao = as.getBean("accountDao2", IAccountDao.class);

        Account account = accountDao.findAccountById(1);
        System.out.println(account);

        Account acountByName = accountDao.findAccountByName("bbb");
        System.out.println(acountByName);

        acountByName.setMoney(1300f);
        accountDao.updateAccount(acountByName);
    }
}
