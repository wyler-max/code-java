package org.example.practicescaffold.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC 测试
 *
 * 1、数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库链接池可解决此问题。
 * 2、Sql 语句在代码中硬编码，造成代码不易维护，实际应用 sql 变化的可能较大，sql 变动需要改变 java
 * 代码。
 * 3、使用 preparedStatement 向占有位符号传参数存在硬编码，因为 sql 语句的 where 条件不一定，可能
 * 多也可能少，修改 sql 还要修改代码，系统不易维护。
 * 4、对结果集解析存在硬编码（查询列名），sql 变化导致解析代码变化，系统不易维护，如果能将数据库记
 * 录封装成 pojo 对象解析比较方便。
 */
public class JdbcController {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 通过驱动管理类，获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mybatis?characterEncoding=utf-8",
                    "root", "123456");
            // 定义sql语句，？表示占位符
            String sql = "select * from user where username = ?";
            // 获取预处理 statement
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数，第一个参数为 sql 语句中的参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setString(1, "Allen");
            // 执行 sql，查出结果集
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " "
                + resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放结果集资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 释放预处理资源
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 释放db连接资源
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
