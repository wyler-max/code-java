package org.example.practicescaffold.db.dao.biz;

import java.util.List;

import org.example.practicescaffold.db.model.biz.User;

/**
 * mybatis mapper接口
 */
// @Mapper // 注释掉防止启动失败
public interface UserMapper {

    List<User> queryUserList();
}
