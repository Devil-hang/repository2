package com.caibei.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化
 */
public class DateFormat {

    public static String dateFormat(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date).toString();
    }

    public  static String formatDate(long date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    //支持YY-MM-DD转换成YYYY-MM-DD
    public  static String formatDate(String date,String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat(format);
        String str = null;
        try {
            str = sdf2.format(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}
