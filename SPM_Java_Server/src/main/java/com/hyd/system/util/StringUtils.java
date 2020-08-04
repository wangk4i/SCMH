package com.hyd.system.util;

import com.hyd.system.info.GetCdInfo;
import com.hyd.system.mapper.GetPrimaryKeyMapper;
import com.hyd.system.vo.DictVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by xieshuai on 2020/4/8 11:07
 * 字符操作工具类
 */
@Component
public class StringUtils {


    @Autowired
    private GetPrimaryKeyMapper getPrimaryKeyMapper;



    /**
     * 获得一个日期类型的主键
     * @param info
     * @return
     */
    public String getDateCd(GetCdInfo info){
        getPrimaryKeyMapper.getDataCd(info);
        return info.getRetStr();
    }

    /**
     * 获得一个主键
     * @param preFix 主键前缀
     * @return
     */
    public String getCd(String preFix){
        GetCdInfo info = new GetCdInfo();
        info.setDatetype("yymmdd");
        info.setPrefix(preFix);
        this.getDateCd(info);
        return info.getRetStr();
    }

    /**
     * 获取获取当前时间格式字符串
     * @return
     */
    public static String getDateStr(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
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

    /**
     * 字符串拼接
     * @param strs
     * @return
     */
    public static String addStr(String...strs){
        StringBuffer stringBuffer = new StringBuffer();
        for(String str: strs){
            stringBuffer.append(str);
        }
        return new String(stringBuffer);
    }

    /**
     * 字符串切割(不包含切割字符中的空字符)
     * @param str
     * @param separatorChars
     * @return
     */
    public static String[] split(String str, String separatorChars){
        return org.apache.commons.lang3.StringUtils.split(str, separatorChars);
    }

    /**
     * 字符串切割(包含切割字符串中的空字符)
     * @param str
     * @param separatorChars
     * @return
     */
    public static String[] splitNoBlank(String str, String separatorChars){
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparatorPreserveAllTokens(str, separatorChars);
    }


    /**
     * 字符串相除
     * @param dividor
     * @param dividend
     * @return
     */
    public static String divide(String dividor, String dividend){
        return null;
    }



    /**
     * 数据字典
     * @return
     */
    public  List<DictVo> getDictToFrom(){
        return getPrimaryKeyMapper.dictionaryinfo();
    }

    /**
     * 判断字符串是否为空(不含空格字符)
     * @param cs
     * @return
     */
    public static boolean isEmpty(final CharSequence cs){
        return cs == null || cs.length() == 0;
    }

    /**
     * 判断字符串是否不为空(不含空格字符串)
     * @param cs
     * @return
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 判断字符串是否为空(含空格字符)
     * @param cs
     * @return
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * 判断字符串是否不为空(含空格字符)
     * @param cs
     * @return
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 字符串补位方法,将位数不足size位的字符串从右补入指定字符串
     * @param str 原字符串
     * @param size 补位后长度
     * @param padStr 补位字符
     * @return
     */
    public static String leftPad(String str, int size, String padStr) {
        return org.apache.commons.lang3.StringUtils.leftPad(str, size, padStr);
    }

    /**
     * 字符串补位方法, 将位数不足Size位的字符串从左补入指定字符串
     * @param str 原字符串
     * @param size 补位后长度
     * @param padStr 补位字符串
     * @return
     */
    public static String rightPad(String str, int size, String padStr){
        return org.apache.commons.lang3.StringUtils.rightPad(str, size, padStr);
    }

}
