package com.xuyu.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class TimeUtils {

	/**
	 * 获得当前周一是几号
	 * @param date 当前日期
	 * @return Date
	 */
	public static Date getThisMondayDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	/**
	 * 获取下周对应的日期
	 *
	 * @param date 当前日期
	 * @param i    设定时间距离当前周一的天数
	 * @return String
	 */
	public String getNextweekday(Date date, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisMondayDate(date));
		cal.add(Calendar.DATE, i);
		String time = sdf.format(cal.getTime());
		return time;
	}

	/**
	 * 获取下周对应的日期
	 *
	 * @param date 当前日期
	 * @param i    设定时间距离当前周一的天数
	 * @return String
	 */
	public String getNext(Date date, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisMondayDate(date));
		cal.add(Calendar.DATE, i);
		String time = sdf.format(cal.getTime());
		return time;
	}

	/**判断传入的时间是星期几
	 * @param dt 传入时间
	 * @return String
	 */
	public String getWeekOfDate(Date dt) {
		String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**
	 * 判断传入时间是星期几
	 * @param dt 传入时间
	 * @return String
	 */
	public String getWeekOfDate1(Date dt) {
		String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**
	 * 获取时间相差的天数
	 * @param date
	 * @return
	 */
	public static int getTimeDifference(Date date){
		Date nowdate =new Date();
		long times1 = date.getTime();
		long times2 = nowdate.getTime();
		int days = (int)((times2-times1)/1000/60/60/24);
		return days;
	}
	/**
	 * 根据周几获取I值
	 * @param a 周几
	 * @return i值
	 */
	public int getI(String a) {
		int i = -1;
		switch (a) {
			case "周一":
				i = 0;
				break;
			case "周二":
				i = 1;
				break;
			case "周三":
				i = 2;
				break;
			case "周四":
				i = 3;
				break;
			case "周五":
				i = 4;
				break;
			case "周六":
				i = 5;
				break;
			case "周日":
				i = 6;
				break;
		}
		return i;
	}

	/**
	 * 根据未来的时间，转换成相对应的商品Item_no
	 * @param time
	 * @return
	 */
	public String change(String time) {
		String a = null;
		int i = -1;
		int m = getI(getWeekOfDate(new Date()));
		int n = getI(time.substring(0, 2));
		if (n > m) {
			i = n - m + m;
		} else {
			i = n - m + 7 + m;
		}
		//System.out.println(i);
		switch (time) {
			case "周一上午":
				a = "-" + getNext(new Date(), i) + "-上午";
				break;
			case "周一下午":
				a = "-" + getNext(new Date(), i) + "-下午";
				break;
			case "周一晚上":
				a = "-" + getNext(new Date(), i) + "-晚上";
				break;
			case "周二上午":
				a = "-" + getNext(new Date(), i) + "-上午";
				break;
			case "周二下午":
				a = "-" + getNext(new Date(), i) + "-下午";
				break;
			case "周二晚上":
				a = "-" + getNext(new Date(), i) + "-晚上";
				break;
			case "周三上午":
				a = "-" + getNext(new Date(), i) + "-上午";
				break;
			case "周三下午":
				a = "-" + getNext(new Date(), i) + "-下午";
				break;
			case "周三晚上":
				a = "-" + getNext(new Date(), i) + "-晚上";
				break;
			case "周四上午":
				a = "-" + getNext(new Date(), i) + "-上午";
				break;
			case "周四下午":
				a = "-" + getNext(new Date(), i) + "-下午";
				break;
			case "周四晚上":
				a = "-" + getNext(new Date(), i) + "-晚上";
				break;
			case "周五上午":
				a = "-" + getNext(new Date(), i) + "-上午";
				break;
			case "周五下午":
				a = "-" + getNext(new Date(), i) + "-下午";
				break;
			case "周五晚上":
				a = "-" + getNext(new Date(), i) + "-晚上";
				break;
			case "周六上午":
				a = "-" + getNext(new Date(), i) + "-上午";
				break;
			case "周六下午":
				a = "-" + getNext(new Date(), i) + "-下午";
				break;
			case "周六晚上":
				a = "-" + getNext(new Date(), i) + "-晚上";
				break;
			case "周日上午":
				a = "-" + getNext(new Date(), i) + "-上午";
				break;
			case "周日下午":
				a = "-" + getNext(new Date(), i) + "-下午";
				break;
			case "周日晚上":
				a = "-" + getNext(new Date(), i) + "-晚上";
				break;
			default:
				break;
		}
		return a;
	}

	/**
	 * 根据传入周几，与当天是周几计算i的差值
	 * @param time
	 * @return
	 */
	public int difference(String time) {
		int i = 0;
		int m = getI(getWeekOfDate(new Date()));
		int n = getI(time.substring(0, 2));
		if (n > m) {
			i = n - m + m;
		} else {
			i = n - m + 7 + m;
		}
		return i;
	}

	/*public static void main(String[] arg) {
		System.out.println(getTimeDifference(getThisMondayDate(new Date())));
	}*/
}
