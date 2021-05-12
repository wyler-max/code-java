package org.example.ssm.service;

import org.example.ssm.domain.Account;

import java.util.List;

/**
 * 账户业务层接口
 */
public interface IAccountService {

    public void saveAccount(Account account);

    public List<Account> findAll();
}
