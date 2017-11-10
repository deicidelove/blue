/**
 * 
 */
package com.common.system.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author amkong
 *
 */
public class DateUtil {
	
	public static Date formtString(String date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		return sdf.parse(date);
	}
	
	public static String formtString(Date date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		return sdf.format(date);
	}
	public static String formtStringLong(Date date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
		return sdf.format(date);
	}
	
	public static Date firstDay(){
		Calendar cal = getCal();
        
		//当前时间的周一日期
		Date startTime = cal.getTime(); 
		return startTime;
		
	}
	
	public static Date endDay(){
		Calendar cal = getCal();
		//当前时间的周日时间
		cal.add(Calendar.DATE, 6);
		Date endTime =  cal.getTime(); 
		return endTime;
	}
	
	public static Date getWantDay(int i){
		Calendar cal = getCal();
		//当前时间的周日时间
		cal.add(Calendar.DATE, i);
		Date Time =  cal.getTime(); 
		return Time;
	}
	
	private static Calendar getCal(){
		Calendar cal = Calendar.getInstance();  
        cal.setTime(new Date());  
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
        if (1 == dayWeek) {  
            cal.add(Calendar.DAY_OF_MONTH, -1);  
        }  
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal;
	}
	// //获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
	public static String getdate(int i)  {
		 Calendar cd = Calendar.getInstance();
		 cd.add(Calendar.DATE, i);
		 Date date = cd.getTime();
		 SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
		 return dformat.format(date);
	 }

}
