package org.example.practicescaffold.config.mysql.sharding;

import org.example.practicescaffold.config.mysql.enums.DataSourceType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * sharding 数据源路由
 *
 * 简单的主从分库实现，不支持跨库，最终会指定到一个库里
 */
@Slf4j
public class ShardingDataSource extends AbstractRoutingDataSource {

    private ShardingDataSourceHolder holder;

    public ShardingDataSource(ShardingDataSourceHolder holder) {
        this.holder = holder;
        setDefaultTargetDataSource(holder.getDb().getDefaultDb().getMaster().getDatasource());// 默认库
        setTargetDataSources(holder.getDb().allDataSourceMap());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType dataSourceType = holder.getDatabaseType().get();
        if (dataSourceType == null) {
            log.debug("数据库{}主从未指定，默认使用MASTER", holder.getDb().getDbName());
            dataSourceType = DataSourceType.MASTER;
        }

        if (holder.isSharding()) {
            Integer shardingIndex = holder.getDatabaseShardingIndex().get();
            if (shardingIndex == null) {
                log.debug("数据库{}分片未指定，默认使用0", holder.getDb().getDbName());
                shardingIndex = 0;
            }
            return holder.getDb().findDbKey(shardingIndex, dataSourceType);
        } else {
            log.debug("数据库{}未使用分库", holder.getDb().getDbName());
            return holder.getDb().findDbKey(0, dataSourceType);
        }
    }

}
