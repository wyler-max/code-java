package org.example.practice.practicecode.javalang.grammar.condition;

import java.util.Arrays;

public enum OrderStatusEnum {
    UN_PAID(0, "订单未支付"), PAIDED(1, "订单已支付"), SENDED(2, "已发货"),;

    private int index;
    private String desc;

    public int getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }

    OrderStatusEnum(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    OrderStatusEnum of(int index) {
        return Arrays.stream(OrderStatusEnum.values()).filter(x -> x.index == index).findAny().orElse(null);
    }
}
