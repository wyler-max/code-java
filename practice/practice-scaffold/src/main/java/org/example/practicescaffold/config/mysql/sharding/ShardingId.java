package org.example.practicescaffold.config.mysql.sharding;

import lombok.Data;

/**
 * 
 * @author yijiu.chen
 * @date 2020/04/20
 * @desc 分库分表的切片ID
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
