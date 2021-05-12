package org.example.ssm.service.impl;

import org.example.ssm.springboot.db.IAccountDao;
import org.example.ssm.domain.Account;
import org.example.ssm.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户业务层实现类
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public void saveAccount(Account account) {
        System.out.println("业务层：saveAccount...");
        accountDao.saveAccount(account);
    }

    @Override
    public List<Account> findAll() {
        System.out.println("业务层：findAll...");
        return accountDao.findAll();
    }
}
