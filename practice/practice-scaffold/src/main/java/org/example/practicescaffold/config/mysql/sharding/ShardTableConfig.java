package org.example.practicescaffold.config.mysql.sharding;

import java.io.Serializable;

import lombok.Data;

/**
 * 目前仅支持按id取mod
 */
@Data
public class ShardTableConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private long tableShardingSize;

    public ShardTableConfig(long id, int tableShardingSize) {
        this.id = id;
        this.tableShardingSize = tableShardingSize;
    }

    public int fetchTableShardingSize() {
        // 依次用完库里的表
        return (int)(id % this.tableShardingSize);
    }
}
