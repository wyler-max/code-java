package org.example.practicejava.javaBase.utils;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * 1、SimpleDateFormat 是线程不安全的
 * public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 * 2、是线程安全的
 * 3、Joda-Time
 * Joda-Time与以上两种有所区别，不仅仅可以对时间进行格式化输出，而且可以生成瞬时时间值，并与Calendar、Date等对象相互转化，极大的方便了程序的兼容性
 * Joda-Time的类具有不可变性，因此他们的实例是无法修改的，就跟String的对象一样
 */
public class DateUtil {

    public static final FastDateFormat DATE = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat DATETIME = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static final FastDateFormat DATETIME_HOUR_MINUTE = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
    public static final FastDateFormat DATETIME_MILLISECOND = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");
    public static final FastDateFormat MONTH_DAY = FastDateFormat.getInstance("MM.dd");
    public static final FastDateFormat MONTH_DAY_SIMPLE = FastDateFormat.getInstance("M.dd");
    public static final FastDateFormat HOUR_MINUTE = FastDateFormat.getInstance("HH:mm");

    public static final int WEEK_DAY_HEAD = 1;
    public static final int WEEK_DAY_TAIL = 7;

    public static Date dateParse(FastDateFormat format, String dateString){
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            // TODO: nothing
        }
        return null;
    }

    /**
     * 获取指定时间
     * @param date 参照的时间
     * @param field Calendar属性 SECOND、MINUTE、HOUR、HOUR_OF_DAY 等
     * @param interval 时间间隔；比如获取一小时后的时间：getIntervalDate
     * @return
     */
    public static Date getGivenDate(Date date, int field, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(field, interval);
        return calendar.getTime();
    }

    // 获取当前时间
    public static Date getToday() {
        return DateUtils.truncate(new Date(), Calendar.DATE);
    }

    // 获取明天时间
    public static Date getTomorrow() {
        return DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DATE);
    }

    // 每周第一天
    public static Date getFirstDayOfWeek(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    // 当前天周几
    public static int getDayOfWeek(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        int val = calendar.get(Calendar.DAY_OF_WEEK);
        return val - 1 + (8 - val) / 7 * 7;
    }

    /***************************** 时间计算 ****************************/
    public static void compareTime() {
        String beginTime = "2020-09-10 10:00:00";
        String endTime = "2020-09-10 18:00:00";
        Date date1 = dateParse(DATETIME, beginTime);
        Date date2 = dateParse(DATETIME, endTime);
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        /*assert date1 != null;
        assert date2 != null;*/
        // 比较时间的三种方法
        System.out.println(date1.compareTo(date2)); // -1 0 1
        System.out.println(date1.before(date2)); // true false
        System.out.println(time1 < time2);
    }

    public static void main(String[] args) {
        Date date = getGivenDate(new Date(), Calendar.HOUR_OF_DAY, 3);
        System.out.println(date);
        System.out.println(dateParse(DATE, "2020-09-14"));

        compareTime();
    }
}
