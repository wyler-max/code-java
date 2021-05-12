package org.example.practicescaffold.mybatis.dao;

import org.example.practicescaffold.mybatis.domain.User;
import org.example.practicescaffold.mybatis.domain.UserMap;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * mybatis 使用注解
 */
@CacheNamespace(blocking=true)//mybatis 基于注解方式实现配置二级缓存
public interface IUserAnnotationDao {

    @Results(
            id = "userMap",
            value = {
                    @Result(id=true,column="id",property="userId"),
                    @Result(column="username",property="userName"),
                    @Result(column="sex",property="userSex"),
                    @Result(column="address",property="userAddress"),
                    @Result(column="birthday",property="userBirthday"),
                    @Result(column = "id", property = "accounts",
                            many = @Many(
                                    select = "com.example.mybatis.dao.IAccountAnnotationDao.findByUid",
                                    fetchType = FetchType.LAZY
                            )
                    )
            }
    )

    /**
     * 查询所有用户
     */
    @Select("select * from user")
    List<UserMap> findAll();

    /**
     * 根据 id 查询一个用户
     */
    @Select("select * from user where id = #{id} ")
    @ResultMap("userMap")
    UserMap findById(int userId);

    /**
     * 保存操作
     */
    @Insert("insert into user (username,sex,birthday,address) values (#{username},#{sex},#{birthday},#{address})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = Integer.class, before = false, statement = {"select last_insert_id()"})
    int saveUser(User user);

    /**
     * 更新操作
     */
    @Update("update user set username=#{userName},sex=#{userSex},birthday=#{userBirthday},address=#{userAddress} where id = #{userId} ")
    int updateUser(UserMap user);

    /**
     * 删除操作
     */
    @Delete("delete from user where id = #{id} ")
    int deleteById(Integer userId);

    /**
     * 使用聚合函数
     */
    @Select("select count(*) from user ")
    int findTotal();


    /**
     * 使用模糊查询
     */
    @Select("select * from user where username like concat(concat('%',#{username},'%')) ")
    @ResultMap("userMap")
    List<UserMap> findByName(String userName);

    /**
     * 使用注解实现复杂关系映射
     * 见 IAccountAnnotationDao
     */

}
