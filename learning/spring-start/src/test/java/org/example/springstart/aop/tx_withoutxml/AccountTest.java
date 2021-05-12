package org.example.springstart.aop.tx_withoutxml;

import org.example.springstart.aop.tx_withoutxml.config.SpringConfiguration;
import org.example.springstart.aop.tx_withoutxml.domain.Account;
import org.example.springstart.aop.tx_withoutxml.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 简单的基于Junit 的 Account测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountTest {

    @Autowired
    private IAccountService as;

    @Test
    public void testAccount() {
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testTransfer() {
        as.transfer("bbb", "aaa", 100F);
    }
}
