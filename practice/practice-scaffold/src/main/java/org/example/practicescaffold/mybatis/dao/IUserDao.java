package org.example.practicescaffold.mybatis.dao;

import org.example.practicescaffold.mybatis.domain.QueryIdsVo;
import org.example.practicescaffold.mybatis.domain.QueryVo;
import org.example.practicescaffold.mybatis.domain.User;

import java.util.List;

/**
 * 用户实体类的持久层接口
 * 或者写成：UserDao、UserMapper
 */
public interface IUserDao {



    /**
     * 使用xml 持久化
     */
    List<User> findAll();


    /**
     * 使用注解 持久化
     * 注意：
     * 1. 在使用基于注解的 Mybatis 配置时，请移除 xml 的映射配置（IUserDao.xml）
     * 2. 在配置文件中，将 mapper 标签的 resource 改为class(com.example.mybatis.dao.IUserDao)(其实通用)
     */
    /*@Select("select * from user where id < 45")
    List<User> findAll();*/

    /**
     * 根据 id 查询用户
     * @param userId
     * @return
     */
    User findById(Integer userId);

    int saveUser(User user);

    int updateUser(User user);

    int deleteById(Integer userId);

    List<User> findByName(String userName);

    List<User> findByName2(String userName);

    int findTotal();

    List<User> findByVo(QueryVo vo);

    List<User> findByCondition(User user);

    List<User> findByConditionWhere(User user);

    List<User> findInIds(QueryIdsVo vo);

    /**
     * 一对多：
     * 使用 left join
     */
    List<User> findAllUserJoinAccount();

    /**
     * 多对多：
     * 使用 left join
     */
    List<User> findAllUserJoinRole();

    /**
     * collection 延迟加载
     */
    List<User> findAllByCollection();

    User findByIdCache(int id);
}
