package org.example.practicescaffold.mybatis.client;

import org.example.practicescaffold.mybatis.domain.User;
import org.example.practicescaffold.mybatis.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试 mybatis 基础流程
 */
public class MybatisTest {

    public static void main(String[] args) throws IOException {
        // 读取配置文件，生成代理对象，并执行查询
        // 1. 读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis/SqlMapConfig.xml");
        // 2. 创建 SqlSessionFactory 构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 3. 创建 SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        // 4. 使用 SqlSessionFactory 创建 SqlSession 对象
        SqlSession session = factory.openSession();
        // 5. 使用 SQLSession 创建 dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        // 6. 使用代理对象，执行查询
        List<User> users = userDao.findAll();
        for (User user: users) {
            System.out.println(user);
        }
        // 7. 释放连接
        session.close();
        in.close();
    }
}
