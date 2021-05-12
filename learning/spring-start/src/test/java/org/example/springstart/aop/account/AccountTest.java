package org.example.springstart.aop.account;

import org.example.springstart.aop.account.domain.Account;
import org.example.springstart.aop.account.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author wangyulin
 * @date 2020/7/7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com/example/springstart/aop/account/bean.xml")
public class AccountTest {

    @Autowired
    @Qualifier("accountService")
    private IAccountService as;

    @Autowired
    @Qualifier("accountServiceOld")
    private IAccountService asOld;

    @Autowired
    @Qualifier("proxyAccountService")
    private IAccountService proxyAccountService;

    @Test
    public void testFindAll() {
        List<Account> accounts = as.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testTransfer() {
        as.transfer("aaa", "bbb", 100f);
    }

    @Test
    public void testTransferOld() {
        asOld.transfer("aaa", "bbb", 100f);
    }

    @Test
    public void testProxy() {
        proxyAccountService.transfer("aaa", "bbb", 100f);
    }
}
