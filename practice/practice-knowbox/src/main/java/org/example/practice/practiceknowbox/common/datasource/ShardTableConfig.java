package org.example.practice.practiceknowbox.common.datasource;

import java.io.Serializable;

import lombok.Data;

/**
 * 目前仅支持按id取mod
 *
 * @author yijiu.chen
 * @date 2020/04/20
 */
@Data
public class ShardTableConfig implements Serializable {
    /**
     *
     */
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
