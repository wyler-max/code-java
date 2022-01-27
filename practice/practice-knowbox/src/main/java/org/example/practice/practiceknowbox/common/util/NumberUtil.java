package org.example.practice.practiceknowbox.common.util;

import java.math.BigDecimal;

/**
 * @author yijiu.chen
 * @date 2020/05/19
 */
public class NumberUtil {

    public static BigDecimal HUNDRED = BigDecimal.valueOf(100L);

    public static BigDecimal percentOne(int numerator, int denominator) {
        return percent(BigDecimal.valueOf(numerator), BigDecimal.valueOf(denominator), 1);
    }

    public static BigDecimal percentTwo(int numerator, int denominator) {
        return percent(BigDecimal.valueOf(numerator), BigDecimal.valueOf(denominator), 2);
    }

    public static BigDecimal percentOne(BigDecimal numerator, BigDecimal denominator) {
        return percent(numerator, denominator, 1);
    }

    public static BigDecimal percentTwo(BigDecimal numerator, BigDecimal denominator) {
        return percent(numerator, denominator, 2);
    }

    public static BigDecimal percent(BigDecimal numerator, BigDecimal denominator, int scale) {
        if (numerator == null || denominator == null) {
            return null;
        }

        return numerator.multiply(HUNDRED).divide(denominator, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal percentTwoWithDefault(BigDecimal molecular, BigDecimal denominator) {
        return denominator.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO
                : NumberUtil.percentTwo(molecular, denominator);
    }

    public static BigDecimal max(BigDecimal v1, BigDecimal v2) {
        if (v1 == null) {
            return v2;
        } else if (v2 == null) {
            return v1;
        } else {
            return v1.compareTo(v2) >= 0 ? v1 : v2;
        }

    }

    public static int getPositiveOrZeroNumber(int number) {
        if (number < 0) {
            return number;
        }
        return number;
    }

    public static long getPositiveOrZeroNumber(long number) {
        if (number < 0L) {
            return number;
        }
        return number;
    }
}
