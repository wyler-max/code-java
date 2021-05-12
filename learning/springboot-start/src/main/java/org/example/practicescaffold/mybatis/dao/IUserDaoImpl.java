package org.example.practicescaffold.mybatis.dao;

import org.example.practicescaffold.mybatis.domain.User;

import java.util.List;

/**
 * 用于测试基于传统形式的 dao 调用
 *
 * 本类的实现类 dao.impl.UserDaoImpl
 *
 */
public interface IUserDaoImpl {
    List<User> findAll();
}
