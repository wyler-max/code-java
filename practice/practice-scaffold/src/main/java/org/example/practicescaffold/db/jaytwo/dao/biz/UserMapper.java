package org.example.practicescaffold.db.jaytwo.dao.biz;

import java.util.List;

import org.example.practicescaffold.db.jaytwo.model.biz.User;
import org.springframework.stereotype.Repository;

@Repository("jayTwoUserMapper")
public interface UserMapper {
    List<User> queryUserList();
}
