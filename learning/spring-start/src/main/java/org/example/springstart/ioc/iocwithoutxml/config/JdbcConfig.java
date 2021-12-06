package org.example.springstart.ioc.iocwithoutxml.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 和spring连接数据库相关的配置类
 */
// @Configuration
public class JdbcConfig {

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean(name = "runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(@Qualifier("ds2") DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    @Bean(name = "ds2")
    public DataSource createDataSource() {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "ds1")
    public DataSource createDataSource1() {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/spring2?characterEncoding=utf-8");
            ds.setUser("root");
            ds.setPassword("123456");
            return ds;
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
}
