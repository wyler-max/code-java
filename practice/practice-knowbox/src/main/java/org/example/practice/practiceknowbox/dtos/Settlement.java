package org.example.practice.practiceknowbox.dtos;

import java.util.List;

import lombok.Data;

/**
 * 结算对象
 */
@Data
public class Settlement {

    // 售价
    private long saleAmount;
    // 结算金额
    private long settlementAmount;

    // 折扣列表汇总
    private List<Discount> discountList;
}
