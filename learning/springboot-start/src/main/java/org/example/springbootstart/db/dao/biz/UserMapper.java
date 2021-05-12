package org.example.springbootstart.db.dao.biz;

import org.example.springbootstart.db.model.biz.User;

import java.util.List;

/**
 * mybatis mapper接口
 */
//@Mapper
public interface UserMapper {

    public List<User> queryUserList();
}
