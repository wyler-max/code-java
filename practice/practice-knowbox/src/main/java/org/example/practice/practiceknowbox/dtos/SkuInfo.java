package org.example.practice.practiceknowbox.dtos;

import java.util.List;

import lombok.Data;

/**
 * 商品sku详情
 */
@Data
public class SkuInfo {

    // 商品编号
    private long skuNumber;
    // 商品名称
    private String skuName;
    // 售卖价格
    private long skuSalePrice;
    // 商品数量
    private int skuCount;

    //

    // 商品的折扣列表
    private List<Discount> discountList;
}
