package org.example.practice.practiceknowbox.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import org.example.practice.practiceknowbox.common.datasource.enums.DataSourceType;
import lombok.extern.slf4j.Slf4j;

/**
 * 简单的分库分表和主从实现，不支持跨库，最终会指定到一个库里
 *
 * @author yijiu.chen
 * @date 2020/04/17
 */
@Slf4j
public class ShardingDataSource extends AbstractRoutingDataSource {

    private ShardingDataSourceHolder holder;

    public ShardingDataSource(ShardingDataSourceHolder holder) {
        this.holder = holder;
        // 默认库
        setDefaultTargetDataSource(holder.getDb().getDefaultMasterSlaveDatabaseInfo().getMaster().getDatasource());
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
            log.debug("{}未使用分库", holder.getDb().getDbName());
            return holder.getDb().findDbKey(0, dataSourceType);
        }
    }

}
