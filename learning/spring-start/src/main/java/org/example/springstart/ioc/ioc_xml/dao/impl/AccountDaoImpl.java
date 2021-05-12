package org.example.springstart.ioc.ioc_xml.dao.impl;

import org.example.springstart.ioc.ioc_xml.dao.IAccountDao;

/**
 *
 */
public class AccountDaoImpl implements IAccountDao {
    public AccountDaoImpl() {
        System.out.println("DaoImpl 对象创建了");
    }

    @Override
    public void saveAccount() {
        System.out.println("保持了账户");
    }
}
