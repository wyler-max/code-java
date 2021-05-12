package org.example.practicescaffold.mybatis.dao;

import org.example.practicescaffold.mybatis.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 使用注解实现复杂关系映射
 */
public interface IAccountAnnotationDao {


    @Results(
            id = "accountMap",
            value = {
                    @Result(id = true,column = "id",property = "id"),
                    @Result(column = "uid",property = "uid"),
                    @Result(column = "money",property = "money"),
                    @Result(column = "uid",
                            property = "user",
                            one = @One(select = "com.example.mybatis.dao.IUserDao.findById",
                                    fetchType = FetchType.LAZY)
                    )
            }
    )

    @Select("select * from account ")
    List<Account> findAll();

    @Select("select * from account where uid = #{id} ")
    List<Account> findByUid(int id);
}
