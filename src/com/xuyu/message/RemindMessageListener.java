package com.xuyu.message;

import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 提示消息监听类，程序运行，该功能运行，每天晚上八点运行
 *
 * @author zhangyinghao
 */
public class RemindMessageListener implements ServletContextListener {
	private Timer timer = null;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		if (timer != null) {
			timer.cancel();
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
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		//定制每天的20:00:00执行
		calendar.set(year, month, day, 20, 00, 00);
		java.util.Date date = calendar.getTime();
		timer.schedule(new RemindMessage(), date);
	}

}
