package org.example.practicescaffold.db.springdefault.mapper;

import java.util.List;

import org.example.practicescaffold.db.springdefault.model.User;
import org.springframework.stereotype.Repository;

@Repository("springDefaultUserMapper")
public interface UserMapper {
    List<User> queryUserList();
}
