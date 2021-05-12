package org.example.springstart.ioc.ioc_anno.dao.impl;

import org.example.springstart.ioc.ioc_anno.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository("accountDao2")
public class AccountDaoImpl2 implements IAccountDao {
    public AccountDaoImpl2() {
        System.out.println("DaoImpl2 对象创建了");
    }

    @Override
    public void saveAccount() {
        System.out.println("保持了账户 2222");
    }
}
