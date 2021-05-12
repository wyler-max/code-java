package org.example.springstart.aop.account.service.impl;

import org.example.springstart.aop.account.dao.IAccountDao;
import org.example.springstart.aop.account.domain.Account;
import org.example.springstart.aop.account.service.IAccountService;
import org.example.springstart.aop.account.utils.TransactionManager;

import java.util.List;

/**
 * @author wangyulin
 * @date 2020/7/7
 */
public class AccountServiceImpl_OLD implements IAccountService {

    private IAccountDao accountDao;
    private TransactionManager txManage;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTxManage(TransactionManager txManage) {
        this.txManage = txManage;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Account> findAllAccount() {
        try {
            txManage.beginTransaction();
            List<Account> accounts = accountDao.findAllAccount();
            txManage.commit();
            return accounts;
        } catch (Exception e) {
            txManage.rollback();
            throw new RuntimeException(e);
        } finally {
            txManage.release();
        }
    }

    /**
     * 查询一个
     *
     * @param accountId
     * @return
     */
    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    /**
     * 保存
     *
     * @param account
     */
    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    /**
     * 更新
     *
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    /**
     * 删除
     *
     * @param acccountId
     */
    @Override
    public void deleteAccount(Integer acccountId) {
        accountDao.deleteAccount(acccountId);
    }

    /**
     * 转账
     *
     * @param sourceName 转出账户名称
     * @param targetName 转入账户名称
     * @param money      转账金额
     */
    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        try {
            System.out.println("transfer....");
            txManage.beginTransaction();
            Account source = accountDao.findAccountByName(sourceName);
            Account target = accountDao.findAccountByName(targetName);
            source.setMoney(source.getMoney()-money);
            target.setMoney(target.getMoney()+money);
            accountDao.updateAccount(source);
            int i = 1/0;
            accountDao.updateAccount(target);
            txManage.commit();
            System.out.println("transfer....done");
        } catch (Exception e) {
            txManage.rollback();
            throw new RuntimeException(e);
        } finally {
            txManage.release();
        }
    }
}
