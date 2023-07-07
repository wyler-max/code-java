package org.example.practice.practicespring.transaction;

import java.util.List;

import javax.annotation.Resource;

import org.example.practice.practicespring.db.mapper.OrderInfoMapper;
import org.example.practice.practicespring.db.mapper.UserMapper;
import org.example.practice.practicespring.db.model.OrderInfo;
import org.example.practice.practicespring.db.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestDBController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @GetMapping(value = "/testSelect")
    public void testSelect() {
        log.info("查询用户");
        List<User> users = userMapper.selectAll();
        System.out.println(users);
    }

    @GetMapping(value = "/testInsert")
    public void testInsert() {
        log.info("插入用户");
        User user = new User();
        user.setUserName("haha");
        user.setPassword("123");
        user.setAddr("soho----123");
        System.out.println(userMapper.insert(user));
    }

    @GetMapping(value = "/testUpdate")
    public void testUpdate(@RequestParam("id") Long id, @RequestParam("addr") String addr) {
        log.info("更新用户");
        System.out.println(userMapper.update(id, addr));
    }

    @GetMapping(value = "/testDelete")
    public void testDelete(@RequestParam("id") Long id) {
        log.info("删除用户");
        System.out.println(userMapper.delete(id));
    }

    @GetMapping(value = "/testOrderSelect")
    public void testOrderSelect() {
        log.info("查询订单");
        System.out.println(orderInfoMapper.selectAll());
    }

    @GetMapping(value = "/testOrderInsert")
    public void testOrderInsert() {
        log.info("插入订单");
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserid(123L);
        orderInfo.setUsername("lalala");
        orderInfo.setPrice(5000L);
        System.out.println(orderInfoMapper.insert(orderInfo));
    }
}
