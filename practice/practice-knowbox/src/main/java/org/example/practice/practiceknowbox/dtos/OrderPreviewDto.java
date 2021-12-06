package org.example.practice.practiceknowbox.dtos;

import java.util.List;

import lombok.Data;

/**
 * 订单预览
 */
@Data
public class OrderPreviewDto {

    // 商品列表
    private List<SkuInfo> skuInfoList;
    // 支付渠道
    private List<PayChannel> payChannelList;

    // 结算对象
    private Settlement settlement;
}
