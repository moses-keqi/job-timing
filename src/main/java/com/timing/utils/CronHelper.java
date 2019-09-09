package com.timing.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author HanKeQi
 * @Description
 * @date 2019/5/17 1:19 PM
 **/

public class CronHelper {

    //只执行一次
    public static final String DATEF_ORMAT = "ss mm HH dd MM ?";

    //while跑
    public static final String DATEF_ORMAT_DAY = "ss mm HH * * ?";

    public static final String DATEF_ORMAT_HH_MM_SS = "HH:mm:ss";

    public static final String DEF_DATEF_ORMAT_DAY = "yyyy-MM-dd HH:mm:ss";

    public static String formatDateByPattern(Date date, String cronFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(cronFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    public static String formatDateByPattern(String dateStr, String cronFormat) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(DEF_DATEF_ORMAT_DAY);
        if (!StringUtils.isEmpty(dateStr)) {
            Date date = sdf.parse(dateStr);
            return formatDateByPattern(date, cronFormat);
        }
        return null;
    }

    public static String scheduledCron(String execute) throws Exception {
        try {
            return formatDateByPattern(execute, DATEF_ORMAT);
        }catch (Exception e){
            try {
                if (e instanceof ParseException){
                    SimpleDateFormat sdf = new SimpleDateFormat(DATEF_ORMAT_HH_MM_SS);
                    if (!StringUtils.isEmpty(execute)) {
                        Date date = sdf.parse(execute);
                        return formatDateByPattern(date, DATEF_ORMAT_DAY);
                    }
                }
            }catch (Exception e1){
                throw new Exception();
            }
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        String  test = scheduledCron("11:22:11");
        System.out.print(test);
    }


}
