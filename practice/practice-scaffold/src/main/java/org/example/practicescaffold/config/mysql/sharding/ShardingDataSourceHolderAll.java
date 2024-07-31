package org.example.practicescaffold.config.mysql.sharding;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.example.practicescaffold.config.mysql.enums.DataSourceType;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据源持有者集合，用于多数据源切换、分表
 */
@Slf4j
public class ShardingDataSourceHolderAll implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, ShardingDataSourceHolder> dbHolderMap = Maps.newHashMap();

    private Map<String, Integer> allShardingTableMap = Maps.newHashMap();

    private ThreadLocal<Map<String, ShardTableConfig>> tableConfigHolder = new ThreadLocal<>();

    public ShardingDataSourceHolderAll(List<ShardingDataSourceHolder> holders) {
        Map<String, ShardingDataSourceHolder> tmpMap = Maps.newHashMap();
        Map<String, Integer> tmpTableMap = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(holders)) {
            int total = 0;
            for (ShardingDataSourceHolder holder : holders) {
                tmpMap.put(holder.getDb().getDbName(), holder);
                total += holder.getDb().getShardingTableMap().size();
                tmpTableMap.putAll(holder.getDb().getShardingTableMap());
            }
            Assert.isTrue(tmpMap.size() == holders.size(), "多数据库配置有重名");
            Assert.isTrue(tmpTableMap.size() == total, "数据库的分表表名重复");
        }
        this.dbHolderMap = tmpMap;
        this.allShardingTableMap = tmpTableMap;
    }

    public void setDataSourceType(String dbName, DataSourceType type) {
        ShardingDataSourceHolder holder = dbHolderMap.get(dbName);
        if (holder == null) {
            throw new RuntimeException("数据库使用错误:" + dbName);
        }
        if (DataSourceType.FORCE_MASTER.equals(holder.getDatabaseType().get())) {
            log.debug("数据库当前为:{},使用类型:{},不操作", DataSourceType.FORCE_MASTER, type);
            return;
        }
        log.debug("设置数据库:{},使用类型:{}", holder.getDb().getDbName(), type);
        // log.debug("db: {}", holder.getDb().getDbs());
        holder.getDatabaseType().set(type);
    }

    public void removeDataSourceType(String dbName) {
        ShardingDataSourceHolder holder = dbHolderMap.get(dbName);
        if (holder == null) {
            throw new RuntimeException("数据库使用错误:" + dbName);
        }
        log.debug("设置数据库:{},清除类型", holder.getDb().getDbName());
        holder.getDatabaseType().remove();
    }

    public void setDataSourceShardingIndex(String dbName, int index) {
        ShardingDataSourceHolder holder = dbHolderMap.get(dbName);
        if (holder == null) {
            throw new RuntimeException("数据库使用错误:" + dbName);
        }
        log.debug("设置数据库{}使用shard:{}", holder.getDb().getDbName(), index);
        if (index >= holder.getDb().getShardingSize()) {
            log.debug("设置数据库{}使用shard:{}超过范围,使用默认值");
            index = 0;
        }
        holder.getDatabaseShardingIndex().set(index);
    }

    public void removeDataSourceShardingIndex(String dbName) {
        ShardingDataSourceHolder holder = dbHolderMap.get(dbName);
        if (holder == null) {
            throw new RuntimeException("数据库使用错误:" + dbName);
        }
    }

    public int fetchDatabaseShardingSize(String dbName) {
        ShardingDataSourceHolder holder = dbHolderMap.get(dbName);
        if (holder == null) {
            throw new RuntimeException("数据库使用错误:" + dbName);
        }
        return holder.getDb().getShardingSize();
    }

    public DataSourceType fetchDataSourceType(String dbName) {
        ShardingDataSourceHolder holder = dbHolderMap.get(dbName);
        if (holder == null) {
            throw new RuntimeException("数据库使用错误:" + dbName);
        }
        return holder.getDatabaseType().get();
    }

    public int fetchTableShardSize(String tableName) {
        Integer value = allShardingTableMap.get(tableName);
        if (value == null) {
            throw new RuntimeException("数据库分表策略使用错误:" + tableName);
        }
        return value;
    }

    public void setTableShardInfo(String dbName, String tableName, long id) {
        Map<String, ShardTableConfig> config = tableConfigHolder.get();
        log.debug("设置分表 db:{}, table:{}, id:{}", dbName, tableName, id);
        if (config == null) {
            config = Maps.newConcurrentMap();
            config.put(tableName, new ShardTableConfig(id, fetchTableShardSize(tableName)));
        }
        tableConfigHolder.set(config);
    }

    public void removeTableShardInfo(String tableName) {
        Map<String, ShardTableConfig> config = tableConfigHolder.get();
        if (config != null) {
            ShardTableConfig sc = config.remove(tableName);
            log.debug("分片表配置删除:{}", sc != null);

            if (config.size() == 0) {
                log.debug("分片配置删除");
                tableConfigHolder.remove();
            }
        }
    }

    public ShardTableConfig fetchTableShardingConfig(String tableName) {
        Map<String, ShardTableConfig> config = tableConfigHolder.get();
        ShardTableConfig sc = null;
        if (config != null) {
            sc = config.get(tableName);
        }
        log.debug("表:{}分片配置:{}", tableName, sc != null);
        return sc;
    }
}
