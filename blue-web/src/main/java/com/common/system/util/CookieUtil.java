package com.common.system.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	/**
	 * 获取cookie中 的某个值
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request , String name){
		Cookie[] cookies = request.getCookies();
		if(null == cookies){
			return null;
		}
		for(Cookie cookie: cookies){
			if(cookie.getName().equals(name)){
				return cookie.getValue();
			}
		}
		
		return null;
	}
	
	/**
	 * 设置cookie值
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletResponse response , String name, String value){
		response.addCookie(new Cookie(name,value));
	}
	
}
