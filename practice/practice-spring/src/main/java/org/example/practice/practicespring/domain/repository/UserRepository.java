package org.example.practice.practicespring.domain.repository;

import org.example.practice.practicespring.db.mapper.UserMapper;
import org.example.practice.practicespring.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wangyulin
 * @date 2021/9/17
 */
@Repository
public class UserRepository {
    @Autowired
    private UserMapper userMapper;

    public User selectById(long id) {
        return userMapper.queryById(id);
    }

    public long updateName(long id, String name) {
        return userMapper.updateName(id, name);
    }
}
