package org.example.springstart.ioc.ioccasexml;

import org.example.springstart.ioc.ioccasexml.domain.Account;
import org.example.springstart.ioc.ioccasexml.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com/example/springstart/ioc/ioccasexml/bean.xml")
public class AccountTest {

    @Autowired
    private IAccountService as;

    //@Before
    public void before() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("com/example/springstart/ioc/ioccasexml/bean.xml");
        // 2.从bean容器中获取
        this.as = ac.getBean("accountService", IAccountService.class);
    }

    @Test
    public void testFindAll() {
        //3.执行方法
        List<Account> accounts = as.findAllAccount();
        for(Account account : accounts){
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        //3.执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("test");
        account.setMoney(12345f);
        //3.执行方法
        as.saveAccount(account);

    }

    @Test
    public void testUpdate() {
        //3.执行方法
        Account account = as.findAccountById(4);
        account.setMoney(23456f);
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        //3.执行方法
        as.deleteAccount(4);
    }
}
