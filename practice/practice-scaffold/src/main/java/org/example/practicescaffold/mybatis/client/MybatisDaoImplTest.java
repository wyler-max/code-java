package org.example.practicescaffold.mybatis.client;

import org.example.practicescaffold.mybatis.domain.User;
import org.example.practicescaffold.mybatis.dao.IUserDaoImpl;
import org.example.practicescaffold.mybatis.dao.impl.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 测试传统的 dao+impl方式使用mybatis（不推荐）
 *
 * 建议使用代理模式，在mapper里处理SQL语句
 */
public class MybatisDaoImplTest {

    private InputStream in;
    private IUserDaoImpl userDaoImpl;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("mybatis/SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂对象，创建dao对象
        userDaoImpl = new UserDaoImpl(factory);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //6.释放资源
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        //5.执行查询所有方法
        List<User> users = userDaoImpl.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }
}
