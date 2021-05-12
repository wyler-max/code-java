package org.example.practicescaffold.jdbctemplate.dao.impl;

import org.example.practicescaffold.jdbctemplate.dao.IAccountDao;
import org.example.practicescaffold.jdbctemplate.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 账户的持久层实现类
 * 需要给 dao 注入 JdbcTemplate
 */
public class AccountDaoImpl1 implements IAccountDao {

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 根据Id查询账户
     *
     * @param accountId
     * @return
     */
    @Override
    public Account findAccountById(Integer accountId) {
        List<Account> accounts = jdbcTemplate.query("select * from account where id=?", new BeanPropertyRowMapper<>(Account.class), accountId);
        return accounts.isEmpty()?null:accounts.get(0);
    }

    /**
     * 根据名称查询账户
     *
     * @param accountName
     * @return
     */
    @Override
    public Account findAccountByName(String accountName) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name=?", new BeanPropertyRowMapper<>(Account.class), accountName);
        if (accounts.isEmpty()) {
            return null;
        }
        if (accounts.size() > 1) {
            throw new RuntimeException("结果不唯一");
        }
        return accounts.get(0);
    }

    /**
     * 更新账户
     *
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name=?,money=? where id=?", account.getName(), account.getMoney(), account.getId());
    }
}
