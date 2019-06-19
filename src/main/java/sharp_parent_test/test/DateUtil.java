package sharp_parent_test.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具
 * 
 * @author Pactera
 *
 */
public class DateUtil {

	/**
	 * 获取指定日期是星期几
	 * 
	 * @param date
	 *            日期
	 * @return 对应周日名
	 */

	public static String getWeekOfDate(Date date) {

		String[] weekOfDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

		Calendar calendar = Calendar.getInstance();

		if (date != null) {

			calendar.setTime(date);

		}

		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;

		if (w < 0) {

			w = 0;

		}

		return weekOfDays[w];
	}

	/**
	 * 获取当前日期
	 * 
	 * @return 当前日期，格式为yyyy-MM-dd
	 */
	public static String getCurrentTime() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		return time;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return 当前日期，格式为yyyy年MM月dd日
	 */
	public static String dateFormat(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		return time;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return 当前日期，格式为yyyyMMddHH
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHH");
		String time = format.format(date);
		return time;
	}

	/**
	 * 获取当前日期时间
	 * 
	 * @return 当前日期时间，格式为yyyy-MM-dd HH:00
	 */
	public static String getCurrentDateHour() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00");
		String time = format.format(date);
		return time;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 当前日期，格式为HH:mm
	 */
	public static String getCurrentHour() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("HH:mm");
		String time = format.format(date);
		return time;
	}

	/**
	 * 判断当前时间是否在此时间范围内
	 * 
	 * @param minTime
	 * @param maxTime
	 * @return
	 */
	public static boolean isRangeTime(String minTime, String maxTime) {
		boolean flag = false;
		if (getCurrentHour().compareTo(minTime) >= 0 && getCurrentHour().compareTo(maxTime) <= 0) {
			flag = true;
		}
		return flag;
	}
}
