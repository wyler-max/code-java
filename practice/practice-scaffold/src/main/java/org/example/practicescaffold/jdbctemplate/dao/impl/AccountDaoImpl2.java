package org.example.practicescaffold.jdbctemplate.dao.impl;

import org.example.practicescaffold.jdbctemplate.dao.IAccountDao;
import org.example.practicescaffold.jdbctemplate.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * 账户的持久层实现类
 * 只需要给它的父类注入一个数据源
 */
public class AccountDaoImpl2 extends JdbcDaoSupport implements IAccountDao {

    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 根据Id查询账户
     *
     * @param accountId
     * @return
     */
    @Override
    public Account findAccountById(Integer accountId) {
        List<Account> accounts = getJdbcTemplate().query("select * from account where id=?", new BeanPropertyRowMapper<>(Account.class), accountId);
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
        List<Account> accounts = getJdbcTemplate().query("select * from account where name=?", new BeanPropertyRowMapper<>(Account.class), accountName);
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
        getJdbcTemplate().update("update account set name=?,money=? where id=?", account.getName(), account.getMoney(), account.getId());
    }
}
