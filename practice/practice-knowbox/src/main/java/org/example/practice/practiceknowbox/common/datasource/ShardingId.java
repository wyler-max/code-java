package org.example.practice.practiceknowbox.common.datasource;

import lombok.Data;

/**
 *
 * @author yijiu.chen
 * @date 2020/04/20
 */
@Data
public class ShardingId {

    private long id;

    private long tableId;

    private ShardingId(long id) {
        this.id = id;
    }

    private ShardingId(long id, long tableId) {
        this.id = id;
        this.tableId = tableId;
    }

    public static ShardingId of(long id) {
        return new ShardingId(id);
    }

    public static ShardingId of(long id, long tableId) {
        return new ShardingId(id, tableId);
    }
}
