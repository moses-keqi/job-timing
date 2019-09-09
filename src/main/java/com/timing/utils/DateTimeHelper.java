package com.timing.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/8/22 1:06 PM
 **/

public class DateTimeHelper {

    public static final String DEF_FORMAT = "yyyy-MM-dd";

    public static final String DEF_STANDARD = "yyyy-MM-dd HH:mm:ss";

    public static Date minusDays(int minusDays, String format) throws Exception {
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(new Date()); //设置时间为当前时间
        ca.add(Calendar.DATE, minusDays); //天数减1
        Date createDate = ca.getTime();
        return strFormatDate(dateFormatStr(createDate, format), format);
    }


    public static String minusDaysToStr(int minusDays, String format) throws Exception {
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(new Date()); //设置时间为当前时间
        ca.add(Calendar.DATE, minusDays); //天数减1
        Date createDate = ca.getTime();
        return dateFormatStr(createDate, format);
    }

    public static String dateFormatStr(Date dateTime, String format){
        return new SimpleDateFormat(format).format(dateTime);
    }

    public static long strFormatToInstantLong(String dateTime, String format) throws Exception {
        Date parse = strFormatDate(dateTime, format);
        return parse.toInstant().toEpochMilli();
    }

    public static Date strFormatDate(String dateTime, String format) throws Exception {
        return new SimpleDateFormat(format).parse(dateTime);
    }

}
