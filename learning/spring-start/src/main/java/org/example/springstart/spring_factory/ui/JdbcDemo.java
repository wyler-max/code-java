package org.example.springstart.spring_factory.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 程序的耦合
 *      耦合：程序间的依赖关系
 *          包括：
 *              类之间的依赖
 *              方法间的依赖
 *      解耦：
 *          降低程序间的依赖关系
 *      实际开发中：
 *          应该做到：编译期不依赖，运行时才依赖。
 *      解耦的思路：
 *          第一步：使用反射来创建对象，而避免使用new关键字。
 *          第二步：通过读取配置文件来获取要创建的对象全限定类名
 *
 */
public class JdbcDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. 注册驱动
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        // 使用反射创建对象，避免使用 new关键字，这样就不会在编译器检查依赖关系，只有当运行时才创建对象（注入，IOC）
        Class.forName("com.mysql.jdbc.Driver");

        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "123456");
        String sql = "select * from user ";
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username")
                    + " "
                    + resultSet.getString("address"));
        }
        resultSet.close();
        preparedStatement.close();
        connect.close();
    }
}
