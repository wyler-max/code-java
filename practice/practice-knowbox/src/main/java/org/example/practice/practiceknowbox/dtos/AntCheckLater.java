package org.example.practice.practiceknowbox.dtos;

import lombok.Data;

/**
 * 花呗分期
 */
@Data
public class AntCheckLater {
    // 分期数 3期 6期 12期
    private int term;
    // 每期手续费
    private long everyTermCharged;
    // 每期总费用
    private long everyTermFee;
}
