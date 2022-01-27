package org.example.practice.practiceknowbox.common.datasource;

import java.io.Serializable;

import org.springframework.util.Assert;

import org.example.practice.practiceknowbox.common.datasource.enums.DataSourceType;
import org.example.practice.practiceknowbox.common.datasource.model.ShardingDatabaseInfo;
import lombok.Getter;

/**
 * 数据源配置对应类
 *
 * @author yijiu.chen
 * @date 2020/04/17
 */
@Getter
public class ShardingDataSourceHolder implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ShardingDatabaseInfo db;

    private ThreadLocal<DataSourceType> databaseType = new ThreadLocal<>();

    private ThreadLocal<Integer> databaseShardingIndex = new ThreadLocal<>();

    private boolean sharding;

    public ShardingDataSourceHolder(ShardingDatabaseInfo db) {
        Assert.isTrue(db != null, "数据库配置不能为空");
        this.db = db;
        this.sharding = this.db.getShardingSize() > 1;
    }

}
