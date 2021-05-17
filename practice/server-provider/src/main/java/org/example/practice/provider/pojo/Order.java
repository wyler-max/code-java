package org.example.practice.provider.pojo;

import lombok.Data;

@Data
public class Order {
    private int orderId;
    private int orderPrice;
    private String orderDesc;
}
