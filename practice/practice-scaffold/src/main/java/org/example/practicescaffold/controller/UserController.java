package org.example.practicescaffold.controller;

import org.example.practicescaffold.common.model.UserInfo;
import org.example.practicescaffold.db.dao.biz.UserMapper;
import org.example.practicescaffold.db.model.biz.User;
import org.example.practicescaffold.web.annotation.LoginCheck;
import org.example.practicescaffold.web.annotation.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    public List<User> queryUserList() {
        return userMapper.queryUserList();
        //return null;
    }

    @LoginCheck
    @RequestMapping("/getUserInfo")
    public UserInfo getUserInfo(@LoginUser UserInfo user) {
        return user;
    }
}
