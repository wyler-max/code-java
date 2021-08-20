package org.example.practicescaffold.config.mysql;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.practicescaffold.config.mysql.sharding.DataSourceInfo;
import org.example.practicescaffold.config.mysql.sharding.DataSourceType;
import org.example.practicescaffold.config.mysql.sharding.MasterSlavesDataSourceEntity;
import org.example.practicescaffold.config.mysql.sharding.ShardingDataSource;
import org.example.practicescaffold.config.mysql.sharding.ShardingDataSourceEntity;
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
public class SqlSessionFactoryConfig {

    @Autowired
    private MuitipleDataSourceConfig muitipleDataSourceConfig;

    public SqlSessionFactory commonFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean("jayDefaultDataSource")
    public DataSource jayDefaultDataSource() {
        log.info("===jayDefaultDataSource=" + muitipleDataSourceConfig.getJaydefault().getMaster().getJdbcUrl());
        return new HikariDataSource(muitipleDataSourceConfig.getJaydefault().getMaster());
    }

    @Bean("jayDefaultSqlSessionFactory")
    public SqlSessionFactory jayDefaultSqlSessionFactory(@Qualifier("jayDefaultDataSource") DataSource dataSource)
        throws Exception {
        return commonFactory(dataSource);
    }

    @Bean("jayOneDataSource")
    public DataSource jayOneDataSource() {
        log.info("===jayOneDataSource=" + muitipleDataSourceConfig.getJayone().getMaster().getJdbcUrl());
        return new HikariDataSource(muitipleDataSourceConfig.getJayone().getMaster());
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
    public ShardingDataSourceHolder baseShardingDataSourceHolder() {
        List<MasterSlavesDataSourceEntity> dbList = Lists.newArrayList();
        for (int i = 0; i < muitipleDataSourceConfig.getJaytwo().size(); i++) {
            MasterSlavesConfig config = muitipleDataSourceConfig.getJaytwo().get(i);
            List<DataSourceInfo> list = Lists.newArrayList();
            list.add(new DataSourceInfo(DataSourceType.MASTER, config.getMaster()));
            log.info("db {} init homework base master", i);
            if (!CollectionUtils.isEmpty(config.getSlaves())) {
                for (int j = 0; j < config.getSlaves().size(); j++) {
                    list.add(new DataSourceInfo(DataSourceType.SLAVE, config.getSlaves().get(j)));
                    log.info("db {} init homework base slave {}", i, j);
                }
            }
            dbList.add(new MasterSlavesDataSourceEntity(list));
        }
        return new ShardingDataSourceHolder(
            new ShardingDataSourceEntity(muitipleDataSourceConfig.getJayTwoName(), null, dbList));
    }

    @Bean("jayTwoDataSource")
    public DataSource jayTwoDataSource(@Qualifier("jayTwoShardingDataSourceHolder") ShardingDataSourceHolder holder) {
        return new ShardingDataSource(holder);
    }

    @Bean("jayTwoTransactionManager")
    @Primary
    public PlatformTransactionManager
        baseHomeworkTransactionManager(@Qualifier("jayTwoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("jayTwoSqlSessionFactory")
    public SqlSessionFactory jayTwoSqlSessionFactory(@Qualifier("jayTwoDataSource") DataSource dataSource)
        throws Exception {
        return commonFactory(dataSource);
    }

    @Bean("jayThreeDataSource")
    public DataSource jayThreeDataSource() {
        log.info("===jayThreeDataSource=" + muitipleDataSourceConfig.getJaythree().get(0).getMaster().getJdbcUrl());
        return new HikariDataSource(muitipleDataSourceConfig.getJaythree().get(0).getMaster());
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
        @Qualifier("jayTwoShardingDataSourceHolder") ShardingDataSourceHolder jayTwoHolder) {
        List<ShardingDataSourceHolder> holderList = Lists.newArrayList();
        holderList.add(jayTwoHolder);
        return new ShardingDataSourceHolderAll(holderList);
    }
}
