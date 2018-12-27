package com.xuyu.tool;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建一个完整的链接
 */
public class MyURL {
    /**
     * 输入的标准日期转换成时间戳
     *
     * @param s
     * @return
     */
    public String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 判断当前时间戳是否有效
     *
     * @param a
     * @return 负数为无效
     */
    public boolean isValid(String a) {
        long t;
        SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            Date start = smdf.parse(a);
            Date y = new Date();
            String year = smdf.format(y);
            Date end = smdf.parse(year);
            t = (end.getTime() - start.getTime()) / 1000 / 60;
            if (t < 24 * 60) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
