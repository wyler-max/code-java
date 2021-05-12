package org.example.ssm.springboot.db;

import org.example.ssm.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 账户持久层接口
 */
public interface IAccountDao {

    @Insert("insert into account (name, money) values (#{name},#{money})")
    public void saveAccount(Account account);

    @Select("select * from account")
    public List<Account> findAll();
}
