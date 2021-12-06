package org.example.practice.practiceknowbox.dtos;

import java.util.List;

import lombok.Data;

/**
 * 支付渠道
 */
@Data
public class PayChannel {

    // 渠道ID
    private long id;
    // 渠道类型 1-微信 2-支付宝 3-花呗分期
    private int type;
    // 渠道名称
    private String name;
    // 渠道简介
    private String desc;
    // 渠道图标
    private String logo;

    // 蚂蚁花呗分期列表
    private List<AntCheckLater> antCheckLaterList;
}
