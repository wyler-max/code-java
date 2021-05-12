package org.example.practicescaffold.mybatis.client;

import org.example.practicescaffold.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试连接池
 */
public class MybatisPoolTest {

    @Test
    public void testSql() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis/SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();

        // 只有当执行到这里时，才会触发 MyBatis 在底层执行 PooledDataSource.PopConnection 方法来创建 java.sql.Connection 对象
        List<User> users = sqlSession.selectList("com.example.mybatis.dao.IUserDao.findAll");
        System.out.println(users.size());
    }
}
