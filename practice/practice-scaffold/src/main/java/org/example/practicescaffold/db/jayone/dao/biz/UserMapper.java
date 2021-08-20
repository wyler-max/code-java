package org.example.practicescaffold.db.jayone.dao.biz;

import java.util.List;

import org.example.practicescaffold.db.jayone.model.biz.User;
import org.springframework.stereotype.Repository;

@Repository("jayOneUserMapper")
public interface UserMapper {
    List<User> queryUserList();
}
