package org.example.practice.practicespring.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.example.practice.practicespring.db.model.User;

@Mapper
public interface UserMapper {
    List<User> selectAll();

    User selectById(long id);

    int insert(User user);

    int update(long id, String addr);

    int delete(long id);
}
