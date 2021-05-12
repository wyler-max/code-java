package org.example.springstart.ioc.ioc_xml.service.impl;

import org.example.springstart.ioc.ioc_xml.dao.IAccountDao;
import org.example.springstart.ioc.ioc_xml.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public AccountServiceImpl() {
        System.out.println("ServiceImpl 对象创建了");
    }

    @Override
    public void saveAccount() {
        System.out.println("service 保持了账户");
        this.accountDao.saveAccount();
    }
}
