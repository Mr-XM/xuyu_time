package com.xuyu.tool;


import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class MysqlTableInitListener implements ServletContextListener {
    private Timer timer = null;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        if (timer != null) {
            timer.cancel();
            //event.getServletContext().log("定时器销毁");
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        timer = new Timer(true);
        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天

        calendar.set(year, month, day, 23, 55, 00);
        java.util.Date date = calendar.getTime();
        TimeUtils abouttine = new TimeUtils();
        if (abouttine.getWeekOfDate(date).equals("周日")) {
            timer.schedule(new MysqlTableInit(), date);
        }
    }

}