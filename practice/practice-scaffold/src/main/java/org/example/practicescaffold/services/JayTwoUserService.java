package org.example.practicescaffold.services;

import java.util.List;

import org.example.practicescaffold.config.mysql.annotation.DatabaseSharding;
import org.example.practicescaffold.config.mysql.annotation.TableSharding;
import org.example.practicescaffold.config.mysql.sharding.ShardingId;
import org.example.practicescaffold.db.jaytwo.dao.biz.UserMapper;
import org.example.practicescaffold.db.jaytwo.model.biz.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangyulin
 * @date 2024/7/29
 */
@Service
public class JayTwoUserService {

    @Autowired
    private UserMapper userMapper;

    @DatabaseSharding(dbName = "jay_two")
    public List<User> queryUserListShardingDatabase(ShardingId shardId) {
        return userMapper.queryUserList();
    }

    @DatabaseSharding(dbName = "jay_two")
    @TableSharding(dbName = "jay_two", tableName = "user")
    public List<User> queryUserListShardingTable(ShardingId shardId) {
        return userMapper.queryUserList();
    }
}
