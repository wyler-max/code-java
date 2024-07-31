package org.example.practicescaffold.config.mysql.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.example.practicescaffold.common.utils.JsonUtil;
import org.example.practicescaffold.config.mysql.enums.DataSourceType;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 分库分表-多数据源实体类
 */
@Getter
@Slf4j
public class ShardingDatabaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dbName;

    private int shardingSize;

    private List<MasterSlavesDatabaseInfo> dbs;

    private MasterSlavesDatabaseInfo defaultDb;

    /**
     * 分表策略
     */
    private Map<String, Integer> shardingTableMap;

    public ShardingDatabaseInfo(String dbName, Map<String, Integer> shardingTableMap,
        List<MasterSlavesDatabaseInfo> dbs) {
        Assert.isTrue(!CollectionUtils.isEmpty(dbs) && StringUtils.isNotBlank(dbName), "数据库信息不为空");
        log.info("ShardingDatabaseInfo shardingTableMap:{}", JsonUtil.toJson(shardingTableMap));
        this.dbs = dbs;
        this.dbName = dbName;
        // 第一个为默认数据库
        this.defaultDb = dbs.get(0);
        this.shardingSize = dbs.size();
        if (shardingTableMap == null) {
            this.shardingTableMap = Collections.emptyMap();
        } else {
            this.shardingTableMap = shardingTableMap;
        }
    }

    public Map<Object, Object> allDataSourceMap() {
        Map<Object, Object> map = Maps.newHashMap();
        int index = 0;
        for (MasterSlavesDatabaseInfo db : this.dbs) {
            // 'jay_two' + index + 'master' => connect
            map.putAll(db.buildMap(buildDbPrefix(index++)));
        }
        return map;
    }

    private String buildDbPrefix(int index) {
        return dbName + index;
    }

    public String findDbKey(int dbIndex, DataSourceType type) {
        if (dbIndex >= shardingSize) {
            log.error("分库索引错误，total:{}, current:{}", shardingSize, dbIndex);
            dbIndex = 0;
        }
        // case: jay_two0SLAVE0
        String dbKey = buildDbPrefix(dbIndex) + dbs.get(dbIndex).findDbKey(type);
        log.debug("find db key: {}", dbKey);
        return dbKey;
    }
}
