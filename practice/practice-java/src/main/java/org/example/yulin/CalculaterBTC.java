package org.example.yulin;

import java.text.DecimalFormat;

import org.junit.Test;

public class CalculaterBTC {

    /**
     * 一般，波动较大的时间段： 5-7点、16-18点、20-22点、23-01点
     *
     * 边界数据： 去不同时间的最大值、和最小值
     *
     * 4hour-top, 4hour-low </br>
     * day-top, day-low ; </br>
     * week-top, week-low ; </br>
     * month-tok, month-low;
     *
     * 设置止盈止损率：10%，20%，40%，50%
     */
    /**
     * 币种：btc、eth、eos； 下面数据根据对应关系计算
     *
     * btc=>eth对标价格、对标率；
     *
     * 例如：35000 => 2000, 35000/ 2000，
     *
     * btc=>eos对标价格、对标率，波动增长率
     *
     * 例如：35000 => 3.9, 35000/ 3.9
     *
     * 波动增长率: btc-up-rate=5%, eth-up-rate=10%, eos-up-rate=15%
     */
    /**
     * 最终目的：
     *
     * 1、根据当前价格和一些波动参数等，计算出合理的买入价格、卖出价格，一次完整操作后可以可以盈利数额
     *
     * 2、根据当前持仓价格、收益率等，计算出合理地清仓、补仓价格
     *
     *
     */
    @Test
    public void processBTC() {

        float topRatioL05 = 0.05F;
        float topRatioL08 = 0.08F;
        float topRatioL1 = 0.1F;
        float topRatioL2 = 0.2F;
        float topRatioL3 = 0.40F;
        float lowRatio = 0.12F;

        // DecimalFormat decimalFormat = new DecimalFormat("1866.71");
        /**
         * 当前价格：1866.71 预估下降 5%，那是价格为 x - x * 5%= x * (95%)
         */
        // float currentPrice = 1866.71F;
        float currentPrice = 3.76F;
        float boughtRatio = topRatioL05;
        float soldRatio = topRatioL08;
        DecimalFormat decimalFormat = new DecimalFormat("0.00000000");
        // 当前价格
        System.out.println("当前价格=" + currentPrice);
        // 下降5%后的购买价格
        float boughtPrice = currentPrice * (1 - boughtRatio);
        System.out.println("下降 " + 100 * boughtRatio + "%, 后的买入价格=" + boughtPrice);
        // 又上涨8%后的抛出价格
        float soldPrice = boughtPrice * (1 + soldRatio);
        System.out.println("上升 " + 100 * soldRatio + "%, 后的抛出价格=" + soldPrice);

    }
}
