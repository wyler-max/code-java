package org.example.practicescaffold.mybatis.client;

import org.example.practicescaffold.mybatis.dao.IUserMapDao;
import org.example.practicescaffold.mybatis.domain.Account;
import org.example.practicescaffold.mybatis.domain.AccountUser;
import org.example.practicescaffold.mybatis.domain.QueryIdsVo;
import org.example.practicescaffold.mybatis.domain.QueryVo;
import org.example.practicescaffold.mybatis.domain.Role;
import org.example.practicescaffold.mybatis.domain.User;
import org.example.practicescaffold.mybatis.domain.UserMap;
import org.example.practicescaffold.mybatis.dao.IAccountDao;
import org.example.practicescaffold.mybatis.dao.IRoleDao;
import org.example.practicescaffold.mybatis.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Mybatis 测试类
 */
public class MybatisCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;

    private IUserDao userDao;
    private IUserMapDao userMapDao;
    private IAccountDao accountDao;
    private IRoleDao roleDao;

    /**
     * 初始化 mybatis 资源
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis/SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession(true);

        // 通过sqlSession 获取代理对象
        userDao = session.getMapper(IUserDao.class);
        userMapDao = session.getMapper(IUserMapDao.class);
        accountDao = session.getMapper(IAccountDao.class);
        roleDao = session.getMapper(IRoleDao.class);

    }

    /**
     * 释放 mybatis 资源
     * @throws IOException
     */
    @After
    public void destroy() throws IOException {
        // 实现 增删改 时要去控制事务的提交
        //session.commit();
        session.close();
        in.close();
    }

    /**
     * 测试查询
     */
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user: users) {
            System.out.println(user);
        }
    }

    /**
     * 测试根据条件查询
     */
    @Test
    public void testFindById() {
        User user = userDao.findById(41);
        System.out.println(user);
    }

    /**
     * 测试新增
     */
    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("new User 01");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京市朝阳区");
        System.out.println("保存之前：" + user);
        userDao.saveUser(user);
        System.out.println("保存之后：" + user);
    }

    /**
     * 测试修改
     */
    @Test
    public void testUpdateUser() {
        User user = userDao.findById(52);
        user.setAddress("Beijing Wangjing");
        int ret = userDao.updateUser(user);
        System.out.println("ret=" + ret);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDeleteById() {
        int ret = userDao.deleteById(53);
        System.out.println("ret=" + ret);
    }

    /**
     * 测试模糊查询，以及 concat
     */
    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("王");
        for (User user: users) {
            System.out.println(user);
        }
    }

    /**
     * 测试模糊查询，以及 #{} & ${}
     */
    @Test
    public void testFindByName2() {
        List<User> users = userDao.findByName2("王");
        for (User user: users) {
            System.out.println(user);
        }
    }

    /**
     * 测试聚合函数
     */
    @Test
    public void testFindTotal() {
        int count = userDao.findTotal();
        System.out.println(count);
    }

    /**
     * 测试 QueryVo 聚合查询条件
     */
    @Test
    public void testFindByVo() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        queryVo.setUser(user);
        List<User> users = userDao.findByVo(queryVo);
        for (User u: users) {
            System.out.println(u);
        }
    }

    /**
     * 测试 mapper：resultMap
     * OGNL 语法
     */
    @Test
    public void testUserMapFindAll() {
        List<UserMap> users = userMapDao.findAll();
        for (UserMap user: users) {
            System.out.println(user);
        }
    }

    /**
     * 测试 mapper：if/sql-include/where
     */
    @Test
    public void testUserFindByCondition() {
        User user = new User();
        user.setUsername("王");
        user.setAddress("金燕龙");
        // List<User> users = userDao.findByCondition(user);
        List<User> users = userDao.findByConditionWhere(user);
        for (User u: users) {
            System.out.println(u);
        }
    }

    /**
     * 测试 mapper：foreach
     *
     * xx != null and xx.size >0
     */
    @Test
    public void testUserFindInIds() {
        QueryIdsVo queryIdsVo = new QueryIdsVo();
        List<Integer> ids = new ArrayList<>();
        ids.add(41);
        ids.add(42);
        ids.add(3);
        ids.add(4);
        ids.add(5);
        queryIdsVo.setIds(ids);
        List<User> users = userDao.findInIds(queryIdsVo);
        for (User u: users) {
            System.out.println(u);
        }
    }

    /**
     * 一对一：（多对一）
     *
     * 需求
     * 查询所有账户信息，关联查询下单用户信息。
     * account 到 user 的一对一
     *
     * 分析：
     * 因为一个账户信息只能供某个用户使用，所以从查询账户信息出发关联查询用户信息为一对一查询。
     * 如果从用户信息出发查询用户下的账户信息则为一对多查询，因为一个用户可以有多个账户
     */

    /**
     * 测试 一对一：
     * 封装AccountUser类，继承Account且添加关联的User属性（推荐）（不用修改实体类，满足OCP原则）
     *
     * 定义专门的 po 类作为输出类型，其中定义了 sql 查询结果集所有的字段。此方法较为简单（推荐使用）。
     */
    @Test
    public void testOneOneFindAllAccount() {
        List<AccountUser> accountUsers = accountDao.findAllAccount();
        for (AccountUser accountUser: accountUsers) {
            System.out.println(accountUser);
        }
    }

    /**
     * 测试 一对一：
     * account 到 user 的一对一
     *
     * 在从表实体类中添加主表实体类的引用，且在Mapper的 resultMap中定义association关联字段
     */
    @Test
    public void testOneOneFindAllUser() {
        List<Account> accounts = accountDao.findAllUser();
        for (Account account: accounts) {
            System.out.println(account);
        }
    }

    /**
     * 一对多：
     *
     * 需求：
     * 查询所有用户信息及用户关联的账户信息
     * user 到 account 的一对多
     *
     * 分析：
     * 用户信息和他的账户信息为一对多关系，并且查询过程中如果用户没有账户信息，此时也要将用户信息
     * 查询出来，我们想到了左外连接查询比较合适
     *
     */
    /**
     * 测试 一对多：
     *
     * 在从表实体类中添加主表实体类的引用，且在Mapper的 resultMap中定义association关联字段
     */
    @Test
    public void testOneMoreFindAllUserJoinAccount() {
        List<User> users = userDao.findAllUserJoinAccount();
        for (User user: users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    /**
     * 多对多：
     *
     * 需求：
     * 实现查询所有角色并且加载它所分配的用户信息
     *
     * 分析：
     * 查询角色我们需要用到Role表，但角色分配的用户的信息我们并不能直接找到用户信息，而是要通过中
     * 间表(USER_ROLE 表)才能关联到用户信息
     */
    /**
     * 测试：多对多
     * role 到 user 的多对多
     */
    @Test
    public void testMoreMoreFindAllRoleJoinUser() {
        List<Role> roles = roleDao.findAll();
        for (Role role: roles) {
            System.out.println(role);
            //System.out.println(role.getUsers());
        }
    }

    /**
     * 需求：
     * 实现查询所有用户信息并关联查询出每个用户的角色列表
     *
     * 分析：
     * 从 User 出发，我们也可以发现一个用户可以具有多个角色，这样用户到角色的关系也还是一对多关系。
     * 这样我们就可以认为 User 与 Role 的多对多关系，可以被拆解成两个一对多关系来实现。
     */
    /**
     * 测试：多对多
     * user 到 role 的多对多
     */
    @Test
    public void testMoreMoreFindAllUserJoinRole() {
        List<User> users = userDao.findAllUserJoinRole();
        for (User user: users) {
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }

    /**
     * association 延迟加载
     *
     * 需求：
     * 查询账户信息同时查询用户信息
     */
    @Test
    public void testLazyLoadAssociationFindAll() {
        List<Account> accounts = accountDao.findAll();
        /*for (Account account: accounts) {
            System.out.println(account);
        }*/
    }

    /**
     * collection 延迟加载
     *
     * 需求：
     * 完成加载用户对象时，查询该用户所拥有的账户信息
     */
    @Test
    public void testLazyLoadCollectionFindAll() {
        List<User> users = userDao.findAllByCollection();
        for (User user: users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    /**
     * mybatis 缓存：一级缓存 sqlsession 级别
     * sqlsession close 或clearCache 可以清空该缓存
     */
    @Test
    public void testFindByIdCache() {
        System.out.println("第一次查询=====");
        User user = userDao.findByIdCache(55);
        System.out.println(user);
        System.out.println("++++++++++++++++++");

        session.clearCache();
        System.out.println("第二次查询=====");
        User user2 = userDao.findByIdCache(55);
        System.out.println(user2);

        System.out.println(user == user2);
    }

    /**
     * mybatis 缓存：一级缓存 sqlsession 级别
     * insert/update/delete 可以清空该缓存
     */
    @Test
    public void testClearCache() {
        System.out.println("第一次查询=====");
        User user = userDao.findByIdCache(55);
        System.out.println(user);
        System.out.println("++++++++++++++++++");

        user.setUsername("update user clear cache2");
        user.setAddress("北京市燕郊区");
        userDao.updateUser(user);
        System.out.println("++++++++++++++++++");

        System.out.println("第二次查询=====");
        User user2 = userDao.findByIdCache(55);
        System.out.println(user2);

        System.out.println(user == user2);
    }

}
