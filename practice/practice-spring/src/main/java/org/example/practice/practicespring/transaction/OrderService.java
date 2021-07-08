package org.example.practice.practicespring.transaction;

import javax.annotation.Resource;

import org.example.practice.practicespring.db.mapper.OrderInfoMapper;
import org.example.practice.practicespring.db.model.OrderInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

    // @formatter:off
    @Resource
    private OrderInfoMapper orderInfoMapper;

    // 公有方法添加事务注解
    public void insertOrderPublic(String userName) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserid(123L);
        orderInfo.setPrice(888L);
        orderInfo.setUsername(userName);
        log.info("order=" + orderInfo);
        orderInfoMapper.insert(orderInfo);
        if (orderInfo.getUsername().contains("order-error")) {
            log.info("模拟抛出异常");
            throw new RuntimeException("public 非法用户名，在线求回滚");
        }
    }
    /**
     * 插入订单，模拟sql2
     */
    @Transactional
    public void insertOrder1(String userName) {
        insertOrderPublic(userName);
    }

    /**
     * 插入订单，模拟sql2 开启新事务
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertOrder2(String userName) {
        insertOrderPublic(userName);
    }

    /**
     * 插入订单，模拟sql2 设置事务嵌套
     */
    @Transactional(propagation = Propagation.NESTED)
    public void insertOrder3(String userName) {
        insertOrderPublic(userName);
    }
}
