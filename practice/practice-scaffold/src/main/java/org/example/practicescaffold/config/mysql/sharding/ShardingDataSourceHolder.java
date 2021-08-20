package org.example.practicescaffold.config.mysql.sharding;

import java.io.Serializable;

import org.springframework.util.Assert;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class ShardingDataSourceHolder implements Serializable {
    private static final long serialVersionUID = 1L;

    private ShardingDataSourceEntity db;

    private ThreadLocal<DataSourceType> databaseType = new ThreadLocal<>();

    private ThreadLocal<Integer> databaseShardingIndex = new ThreadLocal<>();

    private boolean sharding;

    public ShardingDataSourceHolder(ShardingDataSourceEntity db) {
        Assert.isTrue(db != null, "数据库配置不能为空");
        this.db = db;
        this.sharding = this.db.getShardingSize() > 1;
        log.debug("===db={}", db.getDbs());
    }
}
