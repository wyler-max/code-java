package org.example.springstart.ioc.ioccaseanno;

import org.example.springstart.ioc.ioccaseanno.domain.Account;
import org.example.springstart.ioc.ioccaseanno.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author wangyulin
 * @date 2020/7/1
 */
public class AccountTest {

    @Autowired
    private IAccountService as;

    @Before
    public void before() {
        // 改为注解
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("com/example/springstart/ioc/ioccaseanno/bean.xml");
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
