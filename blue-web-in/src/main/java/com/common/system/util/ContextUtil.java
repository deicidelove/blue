/**
 * 
 */
package com.common.system.util;

/**
 * @author amkong
 *
 */
public class ContextUtil {
	
	public static String setConent(String context){
		
		context = context.replace("& lt;", "<");
		context = context.replace("& gt;", ">");
		
		return context;
		
	}

}
