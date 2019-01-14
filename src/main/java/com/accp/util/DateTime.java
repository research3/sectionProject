package com.accp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期与时间
 * 
 * @author yuno
 *
 */
public class DateTime {
	// 时间格式转换器
	private static SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat simpleDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 当前时间
	 * 
	 * @return 当前时间字符串
	 */
	public static String nowDate() {
		String time = simpleDate.format(new Date());
		return time;
	}

	/**
	 * 时间差
	 * 
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @return 天数
	 */
	public static long dateLag(Object start, Object end) {
		try {
			Date s = start instanceof Date ? (Date) start : simpleDate.parse((String) start);
			Date e = end instanceof Date ? (Date) end : simpleDate.parse((String) end);
			return (e.getTime() - s.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			throw new RuntimeException("日期格式不正确:'" + start + "'或'" + end + "'.正确的格式为:'yyyy-MM-dd HH:mm:ss'.");
		}
	}

	/**
	 * 时间差
	 * 
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return 时间戳
	 */
	public static long dateTimeLag(Object start, Object end) {
		try {
			Date s = start instanceof Date ? (Date) start : simpleDateTime.parse((String) start);
			Date e = end instanceof Date ? (Date) end : simpleDateTime.parse((String) end);
			return e.getTime() - s.getTime();
		} catch (Exception e) {
			throw new RuntimeException("日期格式不正确:'" + start + "'或'" + end + "'.正确的格式为:'yyyy-MM-dd HH:mm:ss'.");
		}
	}
}