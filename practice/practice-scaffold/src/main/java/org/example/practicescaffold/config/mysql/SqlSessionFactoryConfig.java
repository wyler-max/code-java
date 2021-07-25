package org.example.practicescaffold.config.mysql;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SqlSessionFactoryConfig {

    @Autowired
    private ShadingDataSourceConfig shadingDataSourceConfig;

    @Bean("jayDefaultDataSource")
    public DataSource dataSource() {
        return new HikariDataSource(shadingDataSourceConfig.getJaydefault().getMaster());
    }

    public SqlSessionFactory commonFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean("jayDefaultSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("jayDefaultDataSource") DataSource dataSource)
        throws Exception {
        return commonFactory(dataSource);
    }
}
