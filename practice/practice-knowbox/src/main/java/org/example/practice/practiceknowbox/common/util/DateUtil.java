package org.example.practice.practiceknowbox.common.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 * @author yijiu.chen
 * @date 2020/04/17
 */
public class DateUtil {

    public static final long ONE_SECOND = 1000L;

    public static final long TEN_SECONDS = 10L * ONE_SECOND;

    public static final long TWENTY_SECONDS = 20L * ONE_SECOND;

    public static final long ONE_MINUTE = 60L * ONE_SECOND;

    public static final long TEN_MINUTE = 10L * ONE_MINUTE;

    public static final long HALF_MINUTE = ONE_MINUTE / 2;

    public static final long HALF_HOUR = 30L * ONE_MINUTE;

    public static final long ONE_HOUR = 60L * ONE_MINUTE;

    public static final long HALF_DAY = 12L * ONE_HOUR;

    public static final long ONE_DAY = 24L * ONE_HOUR;

    public static final long ONE_WEEK = 7L * ONE_DAY;

    public static final FastDateFormat DATE = FastDateFormat.getInstance("yyyy-MM-dd");

    public static final FastDateFormat DATE2 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");

    public static final FastDateFormat S_DATE = FastDateFormat.getInstance("yyyyMMdd");

    public static final FastDateFormat DATETIME = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    public static final FastDateFormat DATETIME_WITHOUT_SECOND = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");

    public static final FastDateFormat FULL_DATETIME = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    public static final FastDateFormat DAY_TIME = FastDateFormat.getInstance("HH:mm");

    public static final FastDateFormat MONTH_DAY = FastDateFormat.getInstance("MM.dd");

    public static final FastDateFormat SIMPLE_MONTH_DAY = FastDateFormat.getInstance("M.dd");

    public static final int WEEK_FIRST_DAY = 1;

    public static final int WEEK_LAST_DAY = 7;
    /**
     * 2020-06-08为周一,那么当前日期与此时间的时间差 除以7后,余数为周几(0~6),结果为间隔周数
     */
    public static final LocalDate BEGIN_DAY = LocalDate.of(2020, 06, 15);

    public static Date safeParse(FastDateFormat format, String val) {
        try {
            return format.parse(val);
        } catch (ParseException e) {
            // do nothing
        }
        return null;
    }

    /**
     * 周一算第一天
     *
     * @param day
     * @return
     */
    public static Date getFirstDayOfWeek(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    public static Date getFirstDayOfMonth(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 当前天周几 周一1，周日7
     *
     * @param day
     * @return
     */
    public static int getDayOfWeek(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        int val = calendar.get(Calendar.DAY_OF_WEEK);
        return val - 1 + (8 - val) / 7 * 7;
    }

    public static Date computeDate(Date day, int field, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(field, value);
        return calendar.getTime();
    }

    public static Date getTomorrow() {
        return DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DATE);
    }

    public static Date getTomorrowDate() {
        try {
            return DATE.parse(DATE.format(getTomorrow()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getToday() {
        return DateUtils.truncate(new Date(), Calendar.DATE);
    }

    public static String getTodayDate() {
        return DATE.format(new Date());
    }

    public static String getStringFullDate(Date date) {
        return DATETIME_WITHOUT_SECOND.format(date);
    }

    public static String getLastWeekBeginDay(String curWeekBeginDay) {
        try {
            Date nowWeekDate = DATE.parse(curWeekBeginDay);
            long nowTime = nowWeekDate.getTime();
            long lastWeekTime = nowTime - ONE_WEEK;
            Date lastWeekDate = new Date(lastWeekTime);
            String lastWeekDayStr = DATE.format(lastWeekDate);
            return lastWeekDayStr;
        } catch (Exception e) {

        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取某个日期的所在周的最后一天
     *
     * @param dayStr
     *            如：2020-08-07（周一）
     * @return 如 08.13（周日）
     */
    public static String getLastDayOfWeek(String dayStr) {
        Date date = DateUtil.safeParse(DateUtil.DATE, dayStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 6);
        return DateUtil.MONTH_DAY.format(calendar.getTime());
    }

    /**
     * 获取当前周的第一天（周一）
     *
     * @return 2020-08-17
     */
    public static String getFirstDayStrOfCurrentWeek() {
        Date firstDayOfWeek = DateUtil.getFirstDayOfWeek(new Date());
        return DateUtil.DATE.format(firstDayOfWeek.getTime());
    }

    /**
     * 获取半年前的时间
     *
     * @return 2020-02-19
     */
    public static String getBeforeHalfYearStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 6);
        return DateUtil.DATE.format(calendar.getTime());
    }

    public static long daysBetween(Date early, Date later) {
        return (DateUtils.truncate(later, Calendar.DATE).getTime() - DateUtils.truncate(early, Calendar.DATE).getTime())
            / ONE_DAY;
    }

    public static void main(String[] args) {
        // System.out.println(getFirstDayOfWeek(new Date()));
        // System.out.println(getDayOfWeek(new Date()));
        System.out.println(daysBetween(getToday(), getTomorrow()));
    }
}
