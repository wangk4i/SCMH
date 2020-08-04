package com.hyd.system.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public static String getSyncDateStr(){
        return org.apache.commons.lang3.time.DateFormatUtils.format(new Date(), "yyyy-MM-dd  HH:mm:ss");
    }

    /**
     * 传入时间格式，返回当前日期的字符串
     * @param pattern yyyMMDD
     * @return
     */
    public static String getDateStr(String pattern){
        if(StringUtils.isEmpty(pattern)){
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
    public static String getDateStr(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = format.parse(dateStr.replaceAll("/","-"));
        return format.format(date);
    }
}
