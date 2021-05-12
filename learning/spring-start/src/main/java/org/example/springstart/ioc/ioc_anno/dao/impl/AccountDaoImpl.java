package org.example.springstart.ioc.ioc_anno.dao.impl;

import org.example.springstart.ioc.ioc_anno.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {
    public AccountDaoImpl() {
        System.out.println("DaoImpl 对象创建了");
    }

    @Override
    public void saveAccount() {
        System.out.println("保持了账户 1111");
    }
}
