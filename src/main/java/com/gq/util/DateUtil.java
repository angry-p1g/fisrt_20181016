package com.gq.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author crc
 * @time 2015-9-15 上午10:05:31
 * @description <pre>
 *
 * </pre>
 *
 */
public class DateUtil {
    /**
     *
     * @description <pre>
     * 返回当前时间YYMMDD格式
     * </pre>
     * @return
     */
    public static String getYYMMDD() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        return sdf.format(d);
    }
    /**
     *
     * @description <pre>
     * 返回当前时间yyMMddHHmmSS格式
     * </pre>
     * @return
     */
    public static String getYYMMDDHHmmSS() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmSS");
        return sdf.format(d);
    }
    /**
     * @description 按传入规则返回
     * @param rule
     * @return
     */
    public static String getStringDate(String rule,Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(rule);
        return sdf.format(date);
    }

    public static Date getStartOfToday(){
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    public static Date getEndOfToday(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    public static Date addSeconds(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * 日期计算
     * @param date 传入的日期
     * @param day 要增加的天数
     * @return
     */
    public static Date getDateAddDay(Date date,int day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

}

