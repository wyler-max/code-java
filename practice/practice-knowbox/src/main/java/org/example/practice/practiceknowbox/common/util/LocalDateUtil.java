package org.example.practice.practiceknowbox.common.util;

import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * @author zhangshuai
 * @date 2021/11/25 6:49 下午
 */
public class LocalDateUtil {

    public static String[] week = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};

    /**
     * 2021年12月1日
     */
    public static final DateTimeFormatter DATE =
        new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('年')
            .appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('月').appendValue(ChronoField.DAY_OF_MONTH, 2)
            .appendLiteral("日").toFormatter(Locale.CHINA);

    public static final DateTimeFormatter MONTH_DATE =
        new DateTimeFormatterBuilder().appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('月')
            .appendValue(ChronoField.DAY_OF_MONTH, 2).appendLiteral("日").toFormatter(Locale.CHINA);

    public static final DateTimeFormatter TIME = new DateTimeFormatterBuilder().appendValue(HOUR_OF_DAY, 2)
        .appendLiteral(':').appendValue(MINUTE_OF_HOUR, 2).optionalStart().toFormatter(Locale.CHINA);

    public static final DateTimeFormatter HOUR_SECOND = DateTimeFormatter.ofPattern("HH:mm", Locale.CHINESE);

    public static String formatWithWeek(LocalDate dateTime, DateTimeFormatter formatter) {
        return dateTime.format(formatter) + " " + week[dateTime.getDayOfWeek().getValue() - 1];
    }

    public static String format(LocalDate dateTime, DateTimeFormatter formatter) {
        return dateTime.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().plusDays(2).getDayOfWeek().getValue());
    }
}
