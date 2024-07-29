package org.example.practice.practicespring.db;

import java.util.List;

import javax.annotation.Resource;

import org.example.practice.practicespring.PracticeSpring;
import org.example.practice.practicespring.db.mapper.OrderInfoMapper;
import org.example.practice.practicespring.db.mapper.UserMapper;
import org.example.practice.practicespring.db.model.OrderInfo;
import org.example.practice.practicespring.db.model.User;
import org.example.practice.practicespring.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试DB连接-MySQL
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PracticeSpring.class)
public class TestDBConnect {

    @Resource
    UserMapper userMapper;
    @Resource
    OrderInfoMapper orderInfoMapper;

    @Test
    public void testUser() {
        List<User> users = userMapper.selectAll();
        System.out.println(JsonUtil.toJson(users));
    }

    @Test
    public void testOrderInfo() {
        List<OrderInfo> orderInfos = orderInfoMapper.selectAll();
        System.out.println(JsonUtil.toJson(orderInfos));
    }
}
