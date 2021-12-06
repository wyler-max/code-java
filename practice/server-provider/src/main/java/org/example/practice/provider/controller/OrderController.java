package org.example.practice.provider.controller;

import org.example.practice.commonutils.pojo.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/p/order")
public class OrderController {

    @GetMapping(value = "/get")
    public Order getProvider() {
        Order order = new Order();
        order.setOrderId(1000);
        order.setOrderPrice(2000);
        order.setOrderDesc("get order");
        return order;
    }

    @PostMapping(value = "/post")
    public Order postProvider() {
        Order order = new Order();
        order.setOrderId(2000);
        order.setOrderPrice(4000);
        order.setOrderDesc("post order");
        return order;
    }
}
