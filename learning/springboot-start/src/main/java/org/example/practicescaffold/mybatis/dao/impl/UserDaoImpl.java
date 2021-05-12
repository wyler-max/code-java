package org.example.practicescaffold.mybatis.dao.impl;

import org.example.practicescaffold.mybatis.domain.User;
import org.example.practicescaffold.mybatis.dao.IUserDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 传统 Dao方式，不建议使用
 */
public class UserDaoImpl implements IUserDaoImpl {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        SqlSession sqlSession = factory.openSession();
        List<User> users = sqlSession.selectList("com.example.mybatis.dao.IUserDao.findAll"); //参数就是能获取配置信息的key
        sqlSession.close();
        return users;
    }
}
