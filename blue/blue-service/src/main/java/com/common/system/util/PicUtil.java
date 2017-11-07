/**
 * 
 */
package com.common.system.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author amkong
 *
 */
public class PicUtil {
	
	public static String upFile( MultipartFile file) throws ParseException{
		String urlTream = "";
		try {
			if (file != null && file.getName() != null && !file.isEmpty()) {
				// 转存文件
				String name = file.getOriginalFilename();
				urlTream = "C:/soft/tomcat/webapps/pics/" + name ;
				file.transferTo(new File(urlTream));
				urlTream = "http://116.62.169.159:8083/pics/" + name ;
				return  urlTream;
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
