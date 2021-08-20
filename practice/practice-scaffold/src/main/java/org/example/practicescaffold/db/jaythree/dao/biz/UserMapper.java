package org.example.practicescaffold.db.jaythree.dao.biz;

import java.util.List;

import org.example.practicescaffold.db.jaythree.model.biz.User;
import org.springframework.stereotype.Repository;

@Repository("jayThreeUserMapper")
public interface UserMapper {
    List<User> queryUserList();
}
