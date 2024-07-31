package org.example.practicescaffold.config.mysql.configuration;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.practicescaffold.config.mysql.enums.DataSourceType;
import org.example.practicescaffold.config.mysql.model.DatabaseInfo;
import org.example.practicescaffold.config.mysql.model.MasterSlavesDatabaseInfo;
import org.example.practicescaffold.config.mysql.model.ShardingDatabaseInfo;
import org.example.practicescaffold.config.mysql.mybatis.interceptor.ShardingTableInterceptor;
import org.example.practicescaffold.config.mysql.mybatis.interceptor.SlowSqlInterceptor;
import org.example.practicescaffold.config.mysql.sharding.ShardingDataSource;
import org.example.practicescaffold.config.mysql.sharding.ShardingDataSourceHolder;
import org.example.practicescaffold.config.mysql.sharding.ShardingDataSourceHolderAll;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DataSourceConfiguration {

    @Autowired
    private ShardingDataSourceConfig shardingDataSourceConfig;

    public SqlSessionFactory commonFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);

        bean.setPlugins(new SlowSqlInterceptor());
        return bean.getObject();
    }

    public SqlSessionFactory jayTwoFactory(DataSource dataSource, ShardingDataSourceHolderAll allHolder)
        throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);

        bean.setPlugins(new ShardingTableInterceptor(allHolder), new SlowSqlInterceptor());
        return bean.getObject();
    }

    @Bean("jayDefaultDataSource")
    public DataSource jayDefaultDataSource() {
        log.info("jayDefaultDataSource" + shardingDataSourceConfig.getJaydefault().getMaster().getJdbcUrl());
        return new HikariDataSource(shardingDataSourceConfig.getJaydefault().getMaster());
    }

    @Bean("jayDefaultSqlSessionFactory")
    public SqlSessionFactory jayDefaultSqlSessionFactory(@Qualifier("jayDefaultDataSource") DataSource dataSource)
        throws Exception {
        return commonFactory(dataSource);
    }

    /**
     * 一主多从分片处理
     */
    @Bean("jayOneShardingDataSourceHolder")
    public ShardingDataSourceHolder jayOneShardingDataSourceHolder() {
        List<MasterSlavesDatabaseInfo> dbList = Lists.newArrayList();
        MasterSlavesConfig config = shardingDataSourceConfig.getJayone();
        List<DatabaseInfo> list = Lists.newArrayList();
        list.add(new DatabaseInfo(DataSourceType.MASTER, config.getMaster()));
        log.info("db {} init jay one master", 1);
        if (!CollectionUtils.isEmpty(config.getSlaves())) {
            for (int j = 0; j < config.getSlaves().size(); j++) {
                list.add(new DatabaseInfo(DataSourceType.SLAVE, config.getSlaves().get(j)));
                log.info("db {} init jay one slave {}", 1, config.getSlaves().get(j));
            }
        }
        dbList.add(new MasterSlavesDatabaseInfo(list));
        return new ShardingDataSourceHolder(
            new ShardingDatabaseInfo(shardingDataSourceConfig.getJayOneName(), null, dbList));
    }

    @Bean("jayOneDataSource")
    public DataSource jayOneDataSource(@Qualifier("jayOneShardingDataSourceHolder") ShardingDataSourceHolder holder) {
        // return new HikariDataSource(muitipleDataSourceConfig.getJayone().getMaster());
        return new ShardingDataSource(holder);
    }

    @Bean("jayOneTransactionManager")
    @Primary
    public PlatformTransactionManager jayOneTransactionManager(@Qualifier("jayOneDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("jayOneSqlSessionFactory")
    public SqlSessionFactory jayOneSqlSessionFactory(@Qualifier("jayOneDataSource") DataSource dataSource)
        throws Exception {
        return commonFactory(dataSource);
    }

    /**
     * 多主多从分片处理
     */
    @Bean("jayTwoShardingDataSourceHolder")
    public ShardingDataSourceHolder jayTwoShardingDataSourceHolder() {
        List<MasterSlavesDatabaseInfo> dbList = Lists.newArrayList();
        for (int i = 0; i < shardingDataSourceConfig.getJaytwo().size(); i++) {
            MasterSlavesConfig config = shardingDataSourceConfig.getJaytwo().get(i);
            List<DatabaseInfo> list = Lists.newArrayList();
            list.add(new DatabaseInfo(DataSourceType.MASTER, config.getMaster()));
            log.info("db {} init jay two master", i);
            if (!CollectionUtils.isEmpty(config.getSlaves())) {
                for (int j = 0; j < config.getSlaves().size(); j++) {
                    list.add(new DatabaseInfo(DataSourceType.SLAVE, config.getSlaves().get(j)));
                    log.info("db {} init jay two slave {}", i, j);
                }
            }
            dbList.add(new MasterSlavesDatabaseInfo(list));
        }
        return new ShardingDataSourceHolder(new ShardingDatabaseInfo(shardingDataSourceConfig.getJayTwoName(),
            shardingDataSourceConfig.getShardingTables(), dbList));
    }

    @Bean("jayTwoDataSource")
    public DataSource jayTwoDataSource(@Qualifier("jayTwoShardingDataSourceHolder") ShardingDataSourceHolder holder) {
        return new ShardingDataSource(holder);
    }

    @Bean("jayTwoTransactionManager")
    @Primary
    public PlatformTransactionManager jayTwoTransactionManager(@Qualifier("jayTwoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("jayTwoSqlSessionFactory")
    public SqlSessionFactory jayTwoSqlSessionFactory(@Qualifier("jayTwoDataSource") DataSource dataSource,
        @Qualifier("shardingDataSourceHolderAll") ShardingDataSourceHolderAll allHolder) throws Exception {
        return jayTwoFactory(dataSource, allHolder);
    }

    @Bean("jayThreeDataSource")
    public DataSource jayThreeDataSource() {
        log.info("jayThreeDataSource" + shardingDataSourceConfig.getJaythree().get(0).getMaster().getJdbcUrl());
        return new HikariDataSource(shardingDataSourceConfig.getJaythree().get(0).getMaster());
    }

    @Bean("jayThreeSqlSessionFactory")
    public SqlSessionFactory jayThreeSqlSessionFactory(@Qualifier("jayThreeDataSource") DataSource dataSource)
        throws Exception {
        return commonFactory(dataSource);
    }

    /**
     * 所有分库分表的 holder
     */
    @Bean("shardingDataSourceHolderAll")
    public ShardingDataSourceHolderAll shardingDataSourceHolderAll(
        @Qualifier("jayOneShardingDataSourceHolder") ShardingDataSourceHolder jayOneHolder,
        @Qualifier("jayTwoShardingDataSourceHolder") ShardingDataSourceHolder jayTwoHolder) {
        List<ShardingDataSourceHolder> holderList = Lists.newArrayList();
        holderList.add(jayOneHolder);
        holderList.add(jayTwoHolder);
        return new ShardingDataSourceHolderAll(holderList);
    }
}
