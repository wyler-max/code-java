package org.example.practicescaffold.db.jaydefault.dao.biz;

import java.util.List;

import org.example.practicescaffold.db.jaydefault.model.biz.User;
import org.springframework.stereotype.Repository;

@Repository("jayDefaultUserMapper")
public interface UserMapper {
    List<User> queryUserList();
}
