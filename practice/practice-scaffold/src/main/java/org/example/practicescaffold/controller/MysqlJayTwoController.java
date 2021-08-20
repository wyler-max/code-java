package org.example.practicescaffold.controller;

import java.util.List;

import javax.annotation.Resource;

import org.example.practicescaffold.common.model.UserInfo;
import org.example.practicescaffold.db.jaytwo.dao.biz.UserMapper;
import org.example.practicescaffold.db.jaytwo.model.biz.User;
import org.example.practicescaffold.web.annotation.LoginCheck;
import org.example.practicescaffold.web.annotation.LoginUser;
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

    @GetMapping("/queryUserList")
    public List<User> queryUserList() {
        return userMapper.queryUserList();
    }

    @LoginCheck
    @RequestMapping("/getUserInfo")
    public UserInfo getUserInfo(@LoginUser UserInfo user) {
        return user;
    }
}
