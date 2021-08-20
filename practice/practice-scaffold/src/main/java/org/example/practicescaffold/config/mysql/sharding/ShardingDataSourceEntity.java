package org.example.practicescaffold.config.mysql.sharding;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
public class ShardingDataSourceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dbName;

    private int shardingSize;

    private List<MasterSlavesDataSourceEntity> dbs;

    private MasterSlavesDataSourceEntity defaultDb;

    /**
     * 分表策略
     */
    private Map<String, Integer> shardingTableMap;

    public ShardingDataSourceEntity(String dbName, Map<String, Integer> shardingTableMap,
        List<MasterSlavesDataSourceEntity> dbs) {
        Assert.isTrue(!CollectionUtils.isEmpty(dbs) && StringUtils.isNotBlank(dbName), "数据库信息不为空");
        this.dbs = dbs;
        this.dbName = dbName;
        this.defaultDb = dbs.get(0);// 第一个为默认数据库
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
        for (MasterSlavesDataSourceEntity db : this.dbs) {
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
        // case: jay_two1master/jay_two1slave1/jay_two1slave2/jay_two2master
        return buildDbPrefix(dbIndex) + dbs.get(dbIndex).findDbKey(type);
    }
}
