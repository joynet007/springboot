package com.liang.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TimeUtil {
	
	
	/**
	 * 把long型转化为2013-8-28 20:25:18
	 * @param time
	 * @return
	 */
	public static String getDisplyTime(long time){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		StringBuffer sb = new StringBuffer();
		sb.append(c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DATE)+" ");
		sb.append(c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));
		
		return sb.toString();
	}
	
	/**
	 * 返回系统的当前时间 long 类型
	 * @return
	 */
	public static long getCurrentTime(){
		return System.currentTimeMillis();
	}
	
	
	public static String getCuurentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");
		Calendar ca = Calendar.getInstance();
		String date = sdf.format(ca.getTime());
		return date;
	}
	
	
	
	public static void main(String args[]){
		System.out.println("---");
		
		TimeUtil tu = new TimeUtil();
		
		System.out.println(tu.getDisplyTime(System.currentTimeMillis()));
		System.out.println(tu.getCuurentDate());
	}
	
}
