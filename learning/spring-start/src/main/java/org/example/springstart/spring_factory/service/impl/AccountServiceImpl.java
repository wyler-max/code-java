package org.example.springstart.spring_factory.service.impl;

import org.example.springstart.spring_factory.dao.IAccountDao;
import org.example.springstart.spring_factory.factory.BeanFactory;
import org.example.springstart.spring_factory.service.IAccountService;

/**
 *
 */
public class AccountServiceImpl implements IAccountService {

    // private IAccountDao accountDao = new AccountDaoImpl();
    private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");

    private int i = 0;

    @Override
    public void saveAccount() {
        ++i;
        accountDao.saveAccount();
        System.out.println(i);
        System.out.println(accountDao);
    }
}
