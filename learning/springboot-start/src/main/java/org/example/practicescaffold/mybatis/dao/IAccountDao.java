package org.example.practicescaffold.mybatis.dao;

import org.example.practicescaffold.mybatis.domain.Account;
import org.example.practicescaffold.mybatis.domain.AccountUser;

import java.util.List;

/**
 * 账户实体类的持久层接口
 */
public interface IAccountDao {

    /**
     * 一对一：
     * 封装AccountUser类，继承Account且添加关联的User属性 (推荐使用)
     */
    List<AccountUser> findAllAccount();

    /**
     * 一对一：
     * 在从表实体类中添加主表实体类的引用，且在Mapper的 resultMap中定义association关联字段
     *
     * 使用 resultMap，定义专门的 resultMap 用于映射一对一查询结果。
     * 通过面向对象的(has a)关系可以得知，我们可以在 Account 类中加入一个 User 类的对象来代表这个账户是哪个用户的。
     */
    List<Account> findAllUser();

    /**
     *  association 实现延迟加载
     *
     */
    List<Account> findAll();

    /**
     * collection 实现延迟加载
     */
    List<Account> findByUid();
}
