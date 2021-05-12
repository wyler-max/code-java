package org.example.practicescaffold.mybatis.client;

import org.example.practicescaffold.mybatis.domain.Account;
import org.example.practicescaffold.mybatis.domain.User;
import org.example.practicescaffold.mybatis.domain.UserMap;
import org.example.practicescaffold.mybatis.dao.IAccountAnnotationDao;
import org.example.practicescaffold.mybatis.dao.IUserAnnotationDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 注解测试
 */
public class MybatisAnnotationTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;

    private IUserAnnotationDao userAnnotationDao;
    private IAccountAnnotationDao accountAnnotationDao;

    /**
     * 初始化 mybatis 资源
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis/SqlMapConfigAnnotation.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession(true);

        // 通过sqlSession 获取代理对象
        userAnnotationDao = session.getMapper(IUserAnnotationDao.class);
        accountAnnotationDao = session.getMapper(IAccountAnnotationDao.class);

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
     * 使用注解实现基本 CRUD
     *
     */
    @Test
    public void testFindAll() {
        List<UserMap> userMaps = userAnnotationDao.findAll();
        for (UserMap user: userMaps) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById() {
        UserMap user = userAnnotationDao.findById(41);
        System.out.println(user);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("new annotation user");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("大屯路");
        int result = userAnnotationDao.saveUser(user);
        System.out.println(user);
        System.out.println(result);
    }

    @Test
    public void testUpdateUser() {
        UserMap userMap = userAnnotationDao.findById(48);
        userMap.setUserName("udpate annotation user");
        userMap.setUserAddress("大屯路");
        userAnnotationDao.updateUser(userMap);
        System.out.println(userMap);
    }

    @Test
    public void testDeleteById() {
        userAnnotationDao.deleteById(50);
    }

    @Test
    public void testFindTotal() {
        int total = userAnnotationDao.findTotal();
        System.out.println(total);
    }

    @Test
    public void testFindByName() {
        List<UserMap> userMaps = userAnnotationDao.findByName("王");
        for (UserMap user: userMaps) {
            System.out.println(user);
        }
    }

    /**
     * 使用注解实现复杂关系映射开发
     *
     * 实现复杂关系映射之前我们可以在映射文件中通过配置<resultMap>来实现，
     * 在使用注解开发时我们需要借助@Results 注解，@Result 注解，@One 注解，@Many 注解
     *
     */

    /**
     * 添加账户的持久层接口并使用注解配置
     *
     * 需求：
     * 加载账户信息时并且加载该账户的用户信息，根据情况可实现延迟加载。（注解方式实现）@One
     *
     */
    @Test
    public void testOneFindAll() {
        List<Account> accounts = accountAnnotationDao.findAll();
        for (Account account: accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    /**
     * 添加用户的持久层接口并使用注解配置
     *
     * 需求：
     * 加载用户信息时并且加载该用户的账户信息，根据情况可实现延迟加载。（注解方式实现）@Many
     *
     * 分析：
     * 一个用户具有多个账户信息，所以形成了用户(User)与账户(Account)之间的一对多关系
     */
    @Test
    public void testManyFindAll() {
        List<UserMap> userMaps = userAnnotationDao.findAll();
        for (UserMap userMap: userMaps) {
            System.out.println(userMap);
            System.out.println(userMap.getAccounts());
        }
    }


}
