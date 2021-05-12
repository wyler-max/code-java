package org.example.practicescaffold.mybatis.dao;

import org.example.practicescaffold.mybatis.domain.Role;

import java.util.List;

/**
 * 角色实体类的持久层接口
 *
 */
public interface IRoleDao {

    /**
     * 多对多：
     * 在从表实体类中添加主表实体类的引用，且在Mapper的 resultMap中定义 collection 关联字段
     */
    List<Role> findAll();


}
