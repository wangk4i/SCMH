package com.hyd.responsexml.util;


import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xieshuai on 2020/5/15 10:29
 */
public class DateUtils {

    /**
     * 获取当前时间格式字符串
     * @return
     */
    public static String getDateStr(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    /**
     * 获取当前时间格式字符串(线程安全)
     * @return
     */
//    public static String getSyncDateStr(){
//        return org.apache.commons.lang3.time.DateFormatUtils.format(new Date(), "yyyy-MM-dd  HH:mm:ss");
//    }

    /**
     * 传入时间格式，返回当前日期的字符串
     * @param pattern yyyMMDD
     * @return
     */
    public static String getDateStr(String pattern){
        if(null==pattern&&pattern.equals("")){
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }

    /**
     * 传入日期，格式化为 指定格式字符串
     * @param dateStr 传入的时间字符串
     * @param pattern 期望转换的时间格式
     * @return 转换后的时间格式字符串
     */
    public static String fomartPatternDate(String dateStr, String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(dateStr.replaceAll("/","-"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format.format(date);
    }

    public static Integer getBetweenDays(String startTimeStr, String endTimeStr) {
        int betweenDays = 0;
        Date startTime = strToDateLong(startTimeStr);
        Date endTime = strToDateLong(endTimeStr);

        long start = startTime.getTime();
        long end = endTime.getTime();

        betweenDays = (int) (Math.abs(end - start)/(24*3600*1000));

        return Integer.valueOf(betweenDays + 1);
    }

    protected static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    private Calendar getDate(String dateStr) {
        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid start date, startDateStr=" + dateStr);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        return calendar;
    }

}
