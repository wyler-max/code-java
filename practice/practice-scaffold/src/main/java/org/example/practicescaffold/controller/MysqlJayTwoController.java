package org.example.practicescaffold.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.example.practicescaffold.common.model.UserInfo;
import org.example.practicescaffold.config.mysql.sharding.ShardingId;
import org.example.practicescaffold.db.jaytwo.dao.biz.UserMapper;
import org.example.practicescaffold.db.jaytwo.model.biz.User;
import org.example.practicescaffold.services.JayTwoUserService;
import org.example.practicescaffold.web.annotation.LoginCheck;
import org.example.practicescaffold.web.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/mysql/jaytwo")
@Slf4j
public class MysqlJayTwoController {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private JayTwoUserService jayTwoUserService;

    @GetMapping("/queryUserList")
    public List<User> queryUserList() {
        return userMapper.queryUserList();
    }

    @GetMapping("/queryUserListShardingDatabase")
    public List<User> queryUserListShardingDatabase() {
        return jayTwoUserService.queryUserListShardingDatabase(ShardingId.of(RandomUtils.nextLong(1, 3)));
    }

    @GetMapping("/queryUserListShardingTable")
    public List<User> queryUserListShardingTable() {
        return jayTwoUserService
            .queryUserListShardingTable(ShardingId.of(RandomUtils.nextLong(1, 3), RandomUtils.nextLong(1, 5)));
    }

    @LoginCheck
    @RequestMapping("/getUserInfo")
    public UserInfo getUserInfo(@LoginUser UserInfo user) {
        return user;
    }
}
