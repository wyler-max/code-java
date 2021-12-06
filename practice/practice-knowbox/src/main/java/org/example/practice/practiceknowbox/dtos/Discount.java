package org.example.practice.practiceknowbox.dtos;

import lombok.Data;

/**
 * 折扣、减免
 */
@Data
public class Discount {
    // 减免名称
    private String name;
    // 减免类型
    private int type;
    // 减免金额
    private long amount;
}
