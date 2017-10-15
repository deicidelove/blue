/**
 * 
 */
package com.common.system.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

}
