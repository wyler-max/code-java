package org.example.practice.practicespring.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.practice.practicespring.db.model.User;

@Mapper
public interface UserMapper {
    List<User> selectAll();

    User selectById(long id);

    @Select("select * from user where id=#{id}")
    User queryById(long id);

    int insert(User user);

    int update(long id, String addr);

    @Select("select count(1) from user")
    int updateName(long id, String name);

    int delete(long id);
}
