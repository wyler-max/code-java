package org.example.practicescaffold.mybatis.dao;

import org.example.practicescaffold.mybatis.domain.UserMap;

import java.util.List;

/**
 * @author wangyulin
 * @date 2020/6/12
 */
public interface IUserMapDao {

    List<UserMap> findAll();
}
