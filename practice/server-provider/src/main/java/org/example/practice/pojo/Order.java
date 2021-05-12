package org.example.practice.pojo;

import lombok.Data;

@Data
public class Order {
    private int orderId;
    private int orderPrice;
    private String orderDesc;
}
